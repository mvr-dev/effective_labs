package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.Customer;
import band.effective.coffieshop.service.impl.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService service;
    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return service.getCustomerById(id);
    }
    @PostMapping
    public Customer addCustomer(@RequestBody Customer Customer){
        return service.addCustomer(Customer);
    }
    @PutMapping("/{id}")
    public  Customer updateCustomer(@PathVariable Long id, @RequestBody Customer Customer){
        Customer.setId(id);
        return service.updateCustomer(Customer);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        service.deleteCustomer(service.getCustomerById(id));
    }
}
