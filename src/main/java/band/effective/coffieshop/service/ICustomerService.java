package band.effective.coffieshop.service;

import band.effective.coffieshop.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id) ;


    Customer updateCustomer(Customer Customer);

    Customer addCustomer(Customer Customer) ;


    public void deleteCustomer(Customer Customer);
}
