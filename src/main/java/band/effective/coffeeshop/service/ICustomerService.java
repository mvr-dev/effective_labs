package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(long id) ;

    Customer updateCustomer(long id,Customer customer);

    Customer addCustomer(Customer customer) ;


    void deleteCustomer(long id);
}
