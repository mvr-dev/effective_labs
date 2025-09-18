package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id) ;

    Customer updateCustomer(Customer Customer);

    Customer addCustomer(Customer Customer) ;


    public void deleteCustomer(Customer Customer);
}
