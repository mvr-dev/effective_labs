package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
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
//    private final CoffeeMapper mapper;
    private final IIngredientService ingredientService;
    private final CoffeeMapper coffeeMapper;

    @Override
    @Transactional
    public List<CoffeeResponseDTO> getAllCoffees() {
        return repository.findAll().stream().map(coffeeMapper::fromEntry).toList();
    }

    @Override
    @Transactional
    public Optional<CoffeeResponseDTO> getCoffeeById(Long id) {
        return repository.findByIdWithIngredients(id).map(coffeeMapper::fromEntry);
    }

    @Override
    @Transactional
    public CoffeeResponseDTO addCoffee(CoffeeRequestDTO coffee) {
        Coffee coffee1 = coffeeMapper.toEntry(coffee);
        return coffeeMapper.fromEntry(repository.save(coffee1));
    }

    @Override
    @Transactional
    public CoffeeResponseDTO updateCoffee(Long id, CoffeeRequestDTO coffeeRequestDTO) {
        Coffee existingCoffee = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND , "No coffee with id "+id
                )
        );

        existingCoffee.setName(coffeeRequestDTO.getName());
        existingCoffee.setPrice(coffeeRequestDTO.getPrice());

        Set<Ingredient> newIngredients = new HashSet<>(
                ingredientService.findAllById(coffeeRequestDTO.getIngredients())
        );
        existingCoffee.setIngredients(newIngredients);

        BigDecimal newCostPrice = newIngredients.stream()
                .map(Ingredient::getCostPerOne)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        existingCoffee.setCostPrice(newCostPrice);

        Coffee updatedCoffee = repository.save(existingCoffee);
        return coffeeMapper.fromEntry(updatedCoffee);
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

    //служебный метод для других классов
    // !! не используется в контроллере !!

    @Override
    public List<Coffee> getAllCoffeesById(List<Long> coffeesId) {
        return repository.findAllById(coffeesId);
    }

}