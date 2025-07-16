package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Customer;
import band.effective.coffieshop.repository.CustomerRepository;
import band.effective.coffieshop.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository repository;
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
    public Customer addCustomer(Customer Customer) {
        return repository.save(Customer);
    }

    @Override
    public void deleteCustomer(Customer Customer) {
        repository.delete(Customer);
    }
}