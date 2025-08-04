package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;
import band.effective.coffieshop.repository.CoffeeRepository;
import band.effective.coffieshop.repository.IngredientRepository;
import band.effective.coffieshop.service.ICoffeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
