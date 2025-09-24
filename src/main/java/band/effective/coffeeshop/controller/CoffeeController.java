package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import band.effective.coffeeshop.service.ICoffeeService;
import band.effective.coffeeshop.service.IIngredientService;
import band.effective.coffeeshop.service.impl.CoffeeService;
import band.effective.coffeeshop.service.mapper.CoffeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/coffees")
@AllArgsConstructor
public class CoffeeController {
    private final ICoffeeService service;
    private final CoffeeMapper mapper;

    @GetMapping
    public List<CoffeeResponseDTO> getAllCoffees(){
        return service.getAllCoffees();

    }
    @GetMapping("/{id}")
    public CoffeeResponseDTO getCoffeeById(@PathVariable Long id){
        var coffee = service.getCoffeeById(id);
        return coffee.orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect id")
        );
    }

    @PutMapping("/{id}")
    public CoffeeResponseDTO updateCoffeeById(@PathVariable long id,@RequestBody CoffeeRequestDTO coffee){
        return service.updateCoffee(id,coffee);
      
    }
    @PostMapping
    public CoffeeResponseDTO postCoffee(@RequestBody CoffeeRequestDTO coffee){
        return  service.addCoffee(coffee);
    }
    @DeleteMapping("/{id}")
    public void deleteCoffee(@PathVariable long id){
        service.deleteCoffee(id);
    }

}