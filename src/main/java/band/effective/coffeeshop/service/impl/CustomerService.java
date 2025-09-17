package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Customer;
import band.effective.coffeeshop.repository.CustomerRepository;
import band.effective.coffeeshop.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository repository;
    private final  EmailService emailService;
    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repository.getReferenceById(id);
    }


    @Override
    public Customer updateCustomer(Customer Customer) {
        return repository.save(Customer);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        emailService.sendMessage(customer.getEmail(),"Hello from effective coffeeshop",
                String.format("Hello, dear %s!\nWe are glad to see you at our coffeeshop!",customer.getName()));
        customer.setWeaklyPoints(BigDecimal.ZERO);
        customer.setPoints(BigDecimal.ZERO);
        return repository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer Customer) {
        repository.delete(Customer);
    }
}