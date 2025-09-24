package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Page<Customer> getAllCustomers(int pageNumber, int pageSize);

    Optional<Customer> getCustomerById(long id) ;

    Customer updateCustomer(long id,Customer customer);

    Customer addCustomer(Customer customer) ;


    void deleteCustomer(long id);
}
