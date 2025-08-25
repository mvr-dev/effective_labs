package band.effective.coffieshop.service.impl;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;
import band.effective.coffieshop.model.weatherResponse.WeatherResponse;
import band.effective.coffieshop.repository.CoffeeRepository;
import band.effective.coffieshop.repository.IngredientRepository;
import band.effective.coffieshop.service.ICoffeeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
