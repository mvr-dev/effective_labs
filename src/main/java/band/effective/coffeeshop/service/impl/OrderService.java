package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.CustomerOrder;
import band.effective.coffeeshop.model.dto.OrderRequestDTO;
import band.effective.coffeeshop.model.dto.OrderResponseDTO;
import band.effective.coffeeshop.repository.OrderRepository;
import band.effective.coffeeshop.service.IOrderService;
import band.effective.coffeeshop.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return mapper.fromEntry(repository.save(mapper.toEntry(order)));
    }

    //Возможно нужно добавить удаление полей Кофе
    @Override
    public void deleteOrder(long id) {
        CustomerOrder order = repository.getReferenceById(id);
        repository.delete(order);
    }
}
