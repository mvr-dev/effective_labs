package band.effective.coffeeshop.service.mapper;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.IngredientRequestDTO;
import band.effective.coffeeshop.model.dto.IngredientResponseDTO;
import band.effective.coffeeshop.service.ICoffeeService;
import band.effective.coffeeshop.service.IIngredientService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IngredientMapper {
    public static IngredientResponseDTO fromEntry(Ingredient ingredient) {
        return IngredientResponseDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .quantity(ingredient.getQuantity())
                .coffees_with(ingredient.getCoffeesWith().stream().map(Coffee::getName).toList())
                .build();
    }
    public static Ingredient toEntry(IngredientRequestDTO ingredientRequest){
        return  Ingredient.builder()
                .name(ingredientRequest.getName())
                .quantity(ingredientRequest.getQuantity())
                .costPerOne(ingredientRequest.getCostPerOne())
                .build();
    }

}
