package band.effective.coffieshop.service;

import band.effective.coffieshop.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IIngredientService {

    List<Ingredient> getAllIngredients();
    List<Ingredient> getAllIngredientsInStock();


    Ingredient getIngredientById(Long id);
    Ingredient getIngredientByName(String name);


    Ingredient updateIngredient(Ingredient ingredient);

    Ingredient addIngredient(Ingredient ingredient);


    void deleteIngredient(Ingredient ingredient);
}
