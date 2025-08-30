package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import band.effective.coffeeshop.service.IIngredientService;
import band.effective.coffeeshop.service.impl.CoffeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/coffees")
@AllArgsConstructor
public class CoffeeController {
    private final CoffeeService service;

    private final IIngredientService ingredientService;

    @GetMapping
    public List<CoffeeResponseDTO> getAllCoffees(){
        var coffees = service.getAllCoffees();
        return coffees.stream().peek(System.out::println).map(CoffeeResponseDTO::fromEntry).toList();
    }
    @GetMapping("/{id}")
    public CoffeeResponseDTO getCoffeeById(@PathVariable Long id){
        System.out.println("get coffee "+id);
        var coffee = service.getCoffeeById(id);
        System.out.println(coffee);
        return CoffeeResponseDTO.fromEntry(coffee);
    }

    @PutMapping("/{id}")
    public Coffee updateCoffeeById(@PathVariable Long id,@RequestBody CoffeeRequestDTO coffee){
        Set<Ingredient> ingredients = new HashSet<>(ingredientService.findAllById(coffee.getIngredients()));
        Coffee coffee1 = Coffee.builder()
                .id(id)
                .name(coffee.getName())
                .ingredients(ingredients)
                .costPrice(ingredients.stream().mapToDouble(Ingredient::getCostPerOne).sum())
                .build();
        System.out.println(coffee);
        System.out.println(coffee1);
        return service.updateCoffee(coffee1);
    }
    @PostMapping
    public Coffee postCoffee(@RequestBody CoffeeRequestDTO coffee){
        Set<Ingredient> ingredients = new HashSet<>(ingredientService.findAllById(coffee.getIngredients()));
        System.out.println(ingredients);
        Coffee coffee1 = Coffee.builder()
                .name(coffee.getName())
                .ingredients(ingredients)
                .price(coffee.getPrice())
                .costPrice(ingredients.stream().mapToDouble(Ingredient::getCostPerOne).sum())
                .build();
        System.out.println(coffee1);
        return service.addCoffee(coffee1);
    }
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable Long id){

        service.deleteCoffee(id);
    }

}
