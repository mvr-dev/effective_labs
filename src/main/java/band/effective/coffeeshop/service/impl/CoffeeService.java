package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.repository.CoffeeRepository;
import band.effective.coffeeshop.service.ICoffeeService;
import band.effective.coffeeshop.service.IIngredientService;
import band.effective.coffeeshop.service.mapper.CoffeeMapper;
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
    private final CoffeeMapper mapper;

//    private final IIngredientService ingredientService;

    @Override
    @Transactional
    public List<Coffee> getAllCoffees() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Coffee> getCoffeeById(Long id) {
        return repository.findByIdWithIngredients(id);
    }

    @Override
    @Transactional
    public Coffee addCoffee(CoffeeRequestDTO coffee) {
        Coffee coffee1 = mapper.toEntry(coffee);
        return repository.save(coffee1);
    }

    @Override
    @Transactional
    public Coffee updateCoffee(Long id,CoffeeRequestDTO coffee) {
        Coffee coffee1 = mapper.toEntry(coffee);
        coffee1.setId(id);
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
    public List<Coffee> getAllCoffeesById(List<Long> coffeesId) {
        return repository.findAllById(coffeesId);
    }

}