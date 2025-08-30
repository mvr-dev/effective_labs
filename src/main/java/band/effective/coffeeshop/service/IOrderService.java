package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.CustomerOrder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IOrderService {
    List<CustomerOrder> getAllOrders();
    CustomerOrder getOrderById(Long id);
    CustomerOrder updateOrder(CustomerOrder customerOrder);
    CustomerOrder addOrder(CustomerOrder customerOrder);
    void deleteOrder(CustomerOrder customerOrder);
}
