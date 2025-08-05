package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Barista;
import band.effective.coffieshop.model.Customer;
import band.effective.coffieshop.model.CustomerOrder;
import band.effective.coffieshop.repository.OrderRepository;
import band.effective.coffieshop.service.IOrderService;
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
        return repository.findByIdWithCoffees(id).orElse(null);
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
