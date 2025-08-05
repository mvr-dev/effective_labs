package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.CustomerOrder;
import band.effective.coffieshop.model.OrderStatus;
import band.effective.coffieshop.model.dto.CoffeeResponseDTO;
import band.effective.coffieshop.model.dto.OrderRequestDTO;
import band.effective.coffieshop.model.dto.OrderResponseDTO;
import band.effective.coffieshop.repository.BaristaRepository;
import band.effective.coffieshop.service.impl.BaristaService;
import band.effective.coffieshop.service.impl.CoffeeService;
import band.effective.coffieshop.service.impl.CustomerService;
import band.effective.coffieshop.service.impl.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService service;

    private final BaristaService baristaService;

    private final CustomerService customerService;

    private final CoffeeService coffeeService;

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
        var coffees = coffeeService.getAllCoffeesById(orderRequestDTO.getCoffeesId());
        System.out.println(coffees);
        CustomerOrder order = CustomerOrder.builder()
                .coffees(coffees)
                .barista(baristaService.getBaristaById(orderRequestDTO.getBaristaId()))
                .price(coffeeService.getAllById(orderRequestDTO.getCoffeesId())
                        .stream().mapToDouble(Coffee::getPrice).sum())
                .status(OrderStatus.COOKING)
                .orderTime(LocalDateTime.now())
                .build();
                if (orderRequestDTO.getCustomerId()!=null){
                    order.setCustomer(customerService.getCustomerById(orderRequestDTO.getCustomerId()));
//                    customerService.getCustomerById(orderRequestDTO.getCustomerId())
                }
                else
                    order.setCustomer(null);
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
            if(requestDTO.getStatus()>=0 && requestDTO.getStatus()<=2){
                order.setStatus(OrderStatus.values()[requestDTO.getStatus()]);
            }
            else
                throw new IllegalArgumentException();
        }
        if(requestDTO.getCoffeesId()!=null){
            order.setCoffees(coffeeService.getAllById(requestDTO.getCoffeesId()));
        }
        System.out.println(order);
        service.updateOrder(order);
        return OrderResponseDTO.fromEntry(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        service.deleteOrder(service.getOrderById(id));
    }

}
