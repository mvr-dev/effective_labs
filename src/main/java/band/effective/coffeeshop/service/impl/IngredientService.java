package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.repository.IngredientRepository;
import band.effective.coffeeshop.service.IIngredientService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Primary
public class IngredientService implements IIngredientService {
    private final IngredientRepository repository;
    @Override
    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    @Override
    public List<Ingredient> getAllIngredientsInStock() {
        return repository.findAll().stream().filter(ingr -> ingr.getQuantity()>0).toList();
    }

    @Override
    @Transactional
    public Ingredient getIngredientById(Long id) {
        return repository.findByIdWithCoffees(id).orElse(null);
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return repository.getIngredientByName(name);
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {

        repository.delete(ingredient);
    }
}
