package band.effective.coffeeshop.service.impl;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.IngredientRequestDTO;
import band.effective.coffeeshop.model.dto.IngredientResponseDTO;
import band.effective.coffeeshop.repository.IngredientRepository;
import band.effective.coffeeshop.service.IIngredientService;
import band.effective.coffeeshop.service.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Primary
public class IngredientService implements IIngredientService {
    private final IngredientRepository repository;
    @Override
    public List<IngredientResponseDTO> getAllIngredients() {
        return repository.findAll().stream().map(IngredientMapper::fromEntry).toList();
    }

    //служебный метод без преобразования в Response
    @Override
    public List<Ingredient> getAllIngredientsInStock() {
        return repository.findAll().stream()
                .filter(ingredient -> ingredient.getQuantity().compareTo(BigDecimal.ZERO)>0)
                .toList();
    }

    @Override
    @Transactional
    public Optional<IngredientResponseDTO> getIngredientById(long id) {
        return repository.findByIdWithCoffees(id).map(IngredientMapper::fromEntry);
    }

    @Override
    public List<Ingredient> findAllById(List<Long> id) {
        return repository.findAllById(id);
    }

    @Override
    public IngredientResponseDTO updateIngredient(long id, IngredientRequestDTO ingredient) {
        var ingredient1 = IngredientMapper.toEntry(ingredient);
        ingredient1.setId(id);
        return IngredientMapper.fromEntry(repository.save(ingredient1));
    }

    @Override
    public IngredientResponseDTO addIngredient(IngredientRequestDTO ingredient) {
        return IngredientMapper.fromEntry(repository.save(IngredientMapper.toEntry(ingredient)));
    }

    @Override
    public void deleteIngredient(long id) {
        var ingredient = repository.getReferenceById(id);
        for (Coffee coffee : ingredient.getCoffeesWith()){
            coffee.getIngredients().remove(ingredient);
        }
        ingredient.getCoffeesWith().clear();
        repository.delete(ingredient);
    }
}
