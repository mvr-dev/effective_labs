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
        return service.getAllOrders().stream().map(
                OrderResponseDTO::fromEntry).toList();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id){
        return OrderResponseDTO.fromEntry(service.getOrderById(id));
    }

    @PostMapping
    public OrderResponseDTO postOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        CustomerOrder order = CustomerOrder.builder()
                .coffees(coffeeService.getAllById(orderRequestDTO.getCoffeesId()))
                .barista(baristaService.getBaristaById(orderRequestDTO.getBaristaId()))
                .price(coffeeService.getAllById(orderRequestDTO.getCoffeesId())
                        .stream().mapToDouble(Coffee::getPrice).sum())
                .status(OrderStatus.COOKING)
                .build();
                if (orderRequestDTO.getCustomerId()!=null){
                    order.setCustomer(customerService.getCustomerById(orderRequestDTO.getCustomerId()));
                }
                else
                    order.setCustomer(null);
                service.addOrder(order);
                return OrderResponseDTO.fromEntry(order);
    }

}
