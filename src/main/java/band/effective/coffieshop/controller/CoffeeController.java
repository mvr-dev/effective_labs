package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.service.impl.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
@AllArgsConstructor
public class CoffeeController {
    private final CoffeeService service;
    @GetMapping
    public List<Coffee> getAllCoffees(){
        return service.getAllCoffees();
    }
    @GetMapping("/{id}")
    public Coffee getCoffeeById(@PathVariable Long id){
        return service.getCoffeeById(id);
    }
    @PutMapping("/{id}")
    public Coffee updateCoffeeById(@RequestBody Coffee coffee){
        return service.updateCoffee(coffee);
    }
    @PostMapping
    public Coffee putCoffee(@RequestBody Coffee coffee){
        System.out.println(coffee);
        return service.addCoffee(coffee);
    }
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id){
        service.deleteCoffee(id);
    }

}
