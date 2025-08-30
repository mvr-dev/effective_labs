package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.CustomerOrder;
import band.effective.coffeeshop.repository.OrderRepository;
import band.effective.coffeeshop.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private OrderRepository repository;

    @Override
    public List<CustomerOrder> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CustomerOrder updateOrder(CustomerOrder customerOrder) {
        return repository.save(customerOrder);
    }

    @Override
    public CustomerOrder addOrder(CustomerOrder customerOrder) {
        return repository.save(customerOrder);
    }

    @Override
    public void deleteOrder(CustomerOrder customerOrder) {
        repository.delete(customerOrder);
    }
}
