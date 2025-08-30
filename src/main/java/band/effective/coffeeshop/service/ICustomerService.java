package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id) ;

    Customer updateCustomer(Customer Customer);

    Customer addCustomer(Customer Customer) ;


    public void deleteCustomer(Customer Customer);
}
