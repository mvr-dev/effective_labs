package band.effective.coffeeshop.service.mapper;

import band.effective.coffeeshop.model.*;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import band.effective.coffeeshop.repository.BaristaRepository;
import band.effective.coffeeshop.repository.CoffeeRepository;
import band.effective.coffeeshop.repository.CustomerRepository;
import band.effective.coffeeshop.repository.PromotionRepository;
import band.effective.coffeeshop.service.IBaristaService;
import band.effective.coffeeshop.service.ICoffeeService;
import band.effective.coffeeshop.service.ICustomerService;
import band.effective.coffeeshop.service.IPromotionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class OrderMapper {
    @Autowired
    private  CoffeeRepository coffeeRepository;
    @Autowired
    private  BaristaRepository baristaRepository;
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private  PromotionRepository promotionRepository;
    private CoffeeMapper coffeeMapper;

    public CustomerOrder toEntry(OrderRequestDTO orderRequestDTO){
        var coffees = coffeeRepository.findAllById(orderRequestDTO.getCoffeesId());
        CustomerOrder order = CustomerOrder.builder()
                .coffees(coffees)
                .barista(baristaRepository.findById(orderRequestDTO.getBaristaId()).get())
                .price(coffees.stream().map(Coffee::getPrice).reduce(BigDecimal.ZERO,
                        BigDecimal::add))
                .status(OrderStatus.COOKING)
                .orderTime(LocalDateTime.now())
                .build();
        if (orderRequestDTO.getCustomerId()!=null){
            Customer customer = customerRepository.findById(orderRequestDTO.getCustomerId()).orElse(null);
            order.setCustomer(customer);
        }
        else
            order.setCustomer(null);
        if (orderRequestDTO.getPromotions()!=null){
            order.setPromotions(promotionRepository
                    .findAllById(orderRequestDTO.getPromotions())
                    .stream().filter(Promotion::isAvailable).toList());
        }
        return order;
    }

    public OrderResponseDTO fromEntry(CustomerOrder order){
        var response = OrderResponseDTO.builder()
                .id(order.getId())
                .barista(order.getBarista())
                .customer(order.getCustomer())
                .coffees(order.getCoffees().stream().map(coffeeMapper::fromEntry).toList())
                .price(order.getCoffees().stream().map(Coffee::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add))
                .status(order.getStatus().toString())
                .time(order.getOrderTime())
//                .promotions(order.getPromotions().stream().map(Promotion::getName).toList())
                .build();
        if (order.getPromotions()!=null){
            response.setPromotions(order.getPromotions().stream().map(Promotion::getName).toList());
            for (Promotion promotion : order.getPromotions()) {
                response.getCoffees().addAll(promotion.getPromotedCoffees().stream().map(coffeeMapper::fromEntry).toList());
                response.setPrice(response.getPrice().add(promotion.getPromotionPrice()));
            }
        }
        return response;
    }
}
