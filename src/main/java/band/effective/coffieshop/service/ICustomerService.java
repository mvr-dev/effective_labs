package band.effective.coffieshop.service;

import band.effective.coffieshop.model.Customer;

import java.util.List;

public interface ICustomerService {

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(Long id) ;


    public Customer updateCustomer(Customer Customer);

    public Customer addCustomer(Customer Customer) ;


    public void deleteCustomer(Customer Customer);
}
