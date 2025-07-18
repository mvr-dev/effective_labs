package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;
import band.effective.coffieshop.model.dto.CoffeeRequestDTO;
import band.effective.coffieshop.repository.CoffeeRepository;
import band.effective.coffieshop.repository.IngredientRepository;
import band.effective.coffieshop.service.impl.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coffees")
@AllArgsConstructor
public class CoffeeController {
    private final CoffeeService service;

    private final CoffeeRepository coffeeRepository;

    private final IngredientRepository ingredientRepository;

    @GetMapping
    public List<Coffee> getAllCoffees(){
        System.out.println(service.getAllCoffees());
        return service.getAllCoffees();
    }
    @GetMapping("/{id}")
    public Coffee getCoffeeById(@PathVariable Long id){
        return service.getCoffeeById(id);
    }
    @PutMapping("/{id}")
    public Coffee updateCoffeeById(@PathVariable Long id,@RequestBody CoffeeRequestDTO coffee){
        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAllById(coffee.getIngredients()));
        Coffee coffee1 = Coffee.builder().id(id).name(coffee.getName()).ingredients(ingredients).build();
        System.out.println(coffee1);
        return service.updateCoffee(coffee1);
    }
    @PostMapping
    public Coffee putCoffee(@RequestBody CoffeeRequestDTO coffee){
        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAllById(coffee.getIngredients()));
        Coffee coffee1 = Coffee.builder().name(coffee.getName()).ingredients(ingredients).build();
        System.out.println(coffee1);
        return service.addCoffee(coffee1);
    }
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id){
        service.deleteCoffee(id);
    }

}
