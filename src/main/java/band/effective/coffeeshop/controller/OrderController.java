package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.*;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import band.effective.coffeeshop.service.*;
import band.effective.coffeeshop.service.impl.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping
    public List<OrderResponseDTO> getOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id){
        return service.getOrderById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect id"));
    }

    @PostMapping
    public OrderResponseDTO postOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return service.addOrder(orderRequestDTO);
    }
    @PutMapping("/{id}")
    public OrderResponseDTO updateOrder(@PathVariable Long id,@RequestBody OrderRequestDTO requestDTO) {
        return service.updateOrder(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        service.deleteOrder(id);
    }

}
