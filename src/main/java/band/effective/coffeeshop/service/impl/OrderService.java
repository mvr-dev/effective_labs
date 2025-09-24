package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Customer;
import band.effective.coffeeshop.model.CustomerOrder;
import band.effective.coffeeshop.model.Promotion;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import band.effective.coffeeshop.repository.OrderRepository;
import band.effective.coffeeshop.service.IOrderService;
import band.effective.coffeeshop.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private OrderRepository repository;
    private OrderMapper mapper;

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return repository.findAll().stream().map(mapper::fromEntry).toList();
    }

    @Override
    public Optional<OrderResponseDTO> getOrderById(Long id) {
        return repository.findById(id).map(mapper::fromEntry);
    }

    @Override
    public OrderResponseDTO updateOrder(long id,OrderRequestDTO order) {
        CustomerOrder customerOrder = mapper.toEntry(order);
        customerOrder.setId(id);
        return mapper.fromEntry(repository.save(customerOrder));
    }

    @Override
    public OrderResponseDTO addOrder(OrderRequestDTO order) {
        CustomerOrder order1 = mapper.toEntry(order);
        order1.setOrderTime(LocalDateTime.now());
        Customer customer = order1.getCustomer();
        if (customer!=null){
            customer.setLastOrder(LocalDate.now());
        }
        return mapper.fromEntry(repository.save(order1));
    }

    @Override
    public void deleteOrder(long id) {
        CustomerOrder order = repository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Incorrect id"));
        for (Promotion promotion: order.getPromotions()){
            promotion.getOrders().remove(order);
        }
        for (Coffee coffee: order.getCoffees()){
            coffee.getCustomerOrders().remove(order);
        }
        repository.delete(order);

    }
}
