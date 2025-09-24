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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Primary
public class IngredientService implements IIngredientService {
    private final IngredientRepository repository;
    private final IngredientMapper ingredientMapper;
    @Override
    public Page<IngredientResponseDTO> getAllIngredients(int pageNumber, int pageSize) {
        return new PageImpl<>(repository.findAll().stream().map(ingredientMapper::fromEntry).toList());
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
        return repository.findByIdWithCoffees(id).map(ingredientMapper::fromEntry);
    }

    @Override
    public List<Ingredient> findAllById(List<Long> id) {
        return repository.findAllById(id);
    }

    @Override
    public IngredientResponseDTO updateIngredient(long id, IngredientRequestDTO ingredient) {
        var ingredient1 = ingredientMapper.toEntry(ingredient);
        ingredient1.setId(id);
        return ingredientMapper.fromEntry(repository.save(ingredient1));
    }

    @Override
    public IngredientResponseDTO addIngredient(IngredientRequestDTO ingredient) {
        return ingredientMapper.fromEntry(repository.save(ingredientMapper.toEntry(ingredient)));
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
