coffeeService

        package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import band.effective.coffeeshop.repository.CoffeeRepository;
import band.effective.coffeeshop.service.ICoffeeService;
import band.effective.coffeeshop.service.IIngredientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CoffeeService implements ICoffeeService {
    private final CoffeeRepository repository;

    private final IIngredientService ingredientService;

    @Override
    @Transactional
    public List<CoffeeResponseDTO> getAllCoffees() {
        return repository.findAll().stream().map(CoffeeResponseDTO::fromEntry).toList();
    }

    @Transactional
    public List<CoffeeResponseDTO> getAllCoffeesById(Iterable<Long> id){
        return repository.findAllById(id).stream()
                .map(CoffeeResponseDTO::fromEntry).toList();
    }

    @Override
    @Transactional
    public Optional<CoffeeResponseDTO> getCoffeeById(Long id) {
        return repository.findByIdWithIngredients(id)
                .map(CoffeeResponseDTO::fromEntry);
    }

    @Override
    @Transactional
    public Coffee addCoffee(CoffeeRequestDTO coffee) {
        Set<Ingredient> ingredients = new HashSet<>(ingredientService.findAllById(coffee.getIngredients()));
        System.out.println(ingredients);
        Coffee coffee1 = Coffee.builder()
                .name(coffee.getName())
                .ingredients(ingredients)
                .price(coffee.getPrice())
                .costPrice(ingredients.stream().map(Ingredient::getCostPerOne).reduce(new BigDecimal("0.0"),BigDecimal::add))
                .build();
        return repository.save(coffee1);
    }

    @Override
    @Transactional
    public Coffee updateCoffee(Long id,CoffeeRequestDTO coffee) {
        Set<Ingredient> ingredients = new HashSet<>(ingredientService.findAllById(coffee.getIngredients()));
        System.out.println(ingredients);
        Coffee coffee1 = Coffee.builder()
                .id(id)
                .name(coffee.getName())
                .ingredients(ingredients)
                .price(coffee.getPrice())
                .costPrice(ingredients.stream().map(Ingredient::getCostPerOne).reduce(new BigDecimal("0.0"),BigDecimal::add))
                .build();
        return repository.save(coffee1);
    }

    @Override
    @Transactional
    public void deleteCoffee(Long id) {
        Coffee coffee = repository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Incorrect id"));
        for(Ingredient ingredient : coffee.getIngredients()){
            ingredient.getCoffeesWith().remove(coffee);
        }
        coffee.getIngredients().clear();
        repository.delete(coffee);
    }

    @Override
    public List<CoffeeResponseDTO> getAllCoffeesById(List<Long> coffeesId) {
        return repository.findAllById(coffeesId).stream().map(CoffeeResponseDTO::fromEntry).toList();
    }

}