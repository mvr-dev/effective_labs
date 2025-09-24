package band.effective.coffeeshop.service;

import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.IngredientRequestDTO;
import band.effective.coffeeshop.model.dto.IngredientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public interface IIngredientService {

    Page<IngredientResponseDTO> getAllIngredients(int pageNumber, int pageSize);
    List<Ingredient> getAllIngredientsInStock();


    Optional<IngredientResponseDTO> getIngredientById(long id);


    List<Ingredient> findAllById(List<Long> id);


    IngredientResponseDTO updateIngredient(long id, IngredientRequestDTO ingredient);

    IngredientResponseDTO addIngredient(IngredientRequestDTO ingredient);


    void deleteIngredient(long id);
}
