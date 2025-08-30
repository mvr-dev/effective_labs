package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Customer;
import band.effective.coffeeshop.service.ICustomerService;
import band.effective.coffeeshop.service.IEmailService;
import band.effective.coffeeshop.service.impl.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService service;
    private final IEmailService emailService;
    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return service.getCustomerById(id);
    }
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){

        emailService.sendMessage(customer.getEmail(),"Hello from effective coffeeshop",
                String.format("Hello, dear %s!\nWe are glad to see you at our coffeeshop!",customer.getName()));
        customer.setWeaklyPoints(BigDecimal.valueOf(0));
        customer.setPoints(BigDecimal.valueOf(0));
        return service.addCustomer(customer);
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
