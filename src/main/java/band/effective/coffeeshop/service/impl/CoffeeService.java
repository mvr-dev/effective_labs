package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.repository.CoffeeRepository;
import band.effective.coffeeshop.repository.IngredientRepository;
import band.effective.coffeeshop.service.ICoffeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CoffeeService implements ICoffeeService {
    private final CoffeeRepository repository;

    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public List<Coffee> getAllCoffees() {
        return repository.findAll();
    }

    @Transactional
    public List<Coffee> getAllCoffeesById(Iterable<Long> id){
        return repository.findAllById(id);
    }

    @Override
    @Transactional
    public Coffee getCoffeeById(Long id) {
        return repository.findByIdWithIngredients(id).orElse(null);
    }

    @Override
    @Transactional
    public Coffee addCoffee(Coffee coffee) {
        return repository.save(coffee);
    }

    @Override
    @Transactional
    public Coffee updateCoffee(Coffee coffee) {
        return repository.save(coffee);
    }

    @Override
    @Transactional
    public void deleteCoffee(Long id) {
        Coffee coffee = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        for(Ingredient ingredient : coffee.getIngredients()){
            ingredient.getCoffeesWith().remove(coffee);
        }
        coffee.getIngredients().clear();
        repository.delete(coffee);
    }

    @Override
    @Transactional
    public Set<Ingredient> getCoffeeIngredients(Long id) {
//        var ingredientsIds =
          return repository.getReferenceById(id).getIngredients();
//        return new HashSet<>(ingredientRepository.findAllById(ingredientsIds));

    }

    public List<Coffee> getAllById(Iterable<Long> ids){
        return repository.findAllById(ids);
    }

}
