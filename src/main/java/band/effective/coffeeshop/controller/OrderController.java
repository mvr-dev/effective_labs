package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.*;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import band.effective.coffeeshop.service.*;
import band.effective.coffeeshop.service.impl.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final IOrderService service;

    private final IBaristaService baristaService;

    private final ICustomerService customerService;

    private final ICoffeeService coffeeService;

    private final IPromotionService promotionService;

    @GetMapping
    public List<OrderResponseDTO> getOrders(){
        return service.getAllOrders().stream().peek(System.out::println).map(
                OrderResponseDTO::fromEntry).toList();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id){
        return OrderResponseDTO.fromEntry(service.getOrderById(id));
    }

    @PostMapping
    public OrderResponseDTO postOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        System.out.println(orderRequestDTO);
        Optional<List<Coffee>> coffees;
        if(orderRequestDTO.getCoffeesId()==null){
            coffees=Optional.empty();
        }
        else{
            coffees = Optional.ofNullable(coffeeService.getAllCoffeesById(orderRequestDTO.getCoffeesId()));
        }
        System.out.println(coffees);
        CustomerOrder order = CustomerOrder.builder()
                .coffees(coffees.orElse(new ArrayList<>()))
                .barista(baristaService.getBaristaById(orderRequestDTO.getBaristaId()))
                .price(BigDecimal.valueOf(coffees.orElse(List.of(Coffee.builder().price(0.).costPrice(0.).name("empty").build()))
                        .stream().mapToDouble(Coffee::getPrice).sum()))
                .status(OrderStatus.COOKING)
                .orderTime(LocalDateTime.now())
                .build();
                if (orderRequestDTO.getCustomerId()!=null){
                    Customer customer = customerService.getCustomerById(orderRequestDTO.getCustomerId());
                    order.setCustomer(customer);
                }
                else
                    order.setCustomer(null);
                if (orderRequestDTO.getPromotions()!=null){
                    order.setPromotions(promotionService
                            .getAllPromotionsById(orderRequestDTO.getPromotions())
                            .stream().filter(Promotion::isAvailable).toList());
                }
        System.out.println(order);
        service.addOrder(order);
        return OrderResponseDTO.fromEntry(order);
    }
    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable Long id,@RequestBody OrderRequestDTO requestDTO){
        CustomerOrder order = service.getOrderById(id);
        System.out.println(requestDTO);
        System.out.println(order);
        if (order.getStatus()!=OrderStatus.COOKING){
            return OrderResponseDTO.fromEntry(order);
        }
        order.setId(id);
        if(requestDTO.getCustomerId()!=null){
            order.setCustomer(customerService.getCustomerById(requestDTO.getCustomerId()));
        }
        if(requestDTO.getBaristaId()!=null){
            order.setBarista(baristaService.getBaristaById(requestDTO.getBaristaId()));
        }
        if(requestDTO.getStatus()!=null){
            if(requestDTO.getStatus()==1){
                order.setStatus(OrderStatus.READY);
                Customer customer = order.getCustomer();
                if(customer!=null){
                    customer.setLastOrder(order.getOrderTime().toLocalDate());
                    customer.setPoints(customer.getPoints().add(order.getPrice().multiply(BigDecimal.valueOf(0.03))));
                }
            }
            else if(requestDTO.getStatus()==2){
                order.setStatus(OrderStatus.CANCELED);
            }
            else
                throw new IllegalArgumentException();
        }
        if(requestDTO.getCoffeesId()!=null){
            order.setCoffees(coffeeService.getAllCoffeesById(requestDTO.getCoffeesId()));
        }
        System.out.println(order);
        service.updateOrder(order);
        return OrderResponseDTO.fromEntry(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        var order =  service.getOrderById(id);
        for(Coffee coffee : order.getCoffees()){
            coffee.getCustomerOrders().remove(order);
        }
        order.getCoffees().clear();
        service.deleteOrder(order);
    }

}
