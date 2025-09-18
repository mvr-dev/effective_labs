package band.effective.coffeeshop.service.mapper;

import band.effective.coffeeshop.model.*;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import band.effective.coffeeshop.service.IBaristaService;
import band.effective.coffeeshop.service.ICoffeeService;
import band.effective.coffeeshop.service.ICustomerService;
import band.effective.coffeeshop.service.IPromotionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private static CoffeeMapper coffeeMapper;
    private ICoffeeService coffeeService;
    private IBaristaService baristaService;
    private ICustomerService customerService;
    private IPromotionService promotionService;
    public CustomerOrder toEntry(OrderRequestDTO orderRequestDTO){
        var coffees = coffeeService.getAllCoffeesById(orderRequestDTO.getCoffeesId());
        CustomerOrder order = CustomerOrder.builder()
                .coffees(coffees)
                .barista(baristaService.getBaristaById(orderRequestDTO.getBaristaId()))
                .price(coffees.stream().map(Coffee::getPrice).reduce(BigDecimal.ZERO,
                        BigDecimal::add))
                .status(OrderStatus.COOKING)
                .orderTime(LocalDateTime.now())
                .build();
        if (orderRequestDTO.getCustomerId()!=null){
            Customer customer = customerService.getCustomerById(orderRequestDTO.getCustomerId()).orElse(null);
            order.setCustomer(customer);
        }
        else
            order.setCustomer(null);
        if (orderRequestDTO.getPromotions()!=null){
            order.setPromotions(promotionService
                    .getAllPromotionsById(orderRequestDTO.getPromotions())
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
