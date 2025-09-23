package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Customer;
import band.effective.coffeeshop.service.ICustomerService;
import band.effective.coffeeshop.service.IEmailService;
import band.effective.coffeeshop.service.impl.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final ICustomerService service;
    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return service.getCustomerById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"incorrect id")
        );
    }
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }
    @PutMapping("/{id}")
    public  Customer updateCustomer(@PathVariable long id, @RequestBody Customer Customer){
        return service.updateCustomer(id,Customer);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable long id){
        service.deleteCustomer(id);
    }
}
