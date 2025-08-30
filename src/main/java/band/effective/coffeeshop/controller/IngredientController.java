package band.effective.coffeeshop.controller;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.IngredientResponseDTO;
import band.effective.coffeeshop.service.impl.IngredientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
public class IngredientController {
    private final IngredientService service;

    @GetMapping
    public List<IngredientResponseDTO> getAllIngredients(){
        var ingredients = service.getAllIngredients();
        return ingredients.stream()
                .map(ingredient -> IngredientResponseDTO.builder()
                        .id(ingredient.getId())
                        .name(ingredient.getName())
                        .quantity(ingredient.getQuantity())
                        .coffees_with(ingredient.getCoffeesWith().stream().map(Coffee::getName).toList())
                        .build()).toList();
    }
    @GetMapping("/stock")
    public List<Ingredient> getAllIngredientsInStock(){
        return service.getAllIngredientsInStock();
    }
    @GetMapping("/{id}")
    public IngredientResponseDTO getIngredientById(@PathVariable Long id){
        var ingredient = service.getIngredientById(id);
        return IngredientResponseDTO.builder()
                .id(id)
                .name(ingredient.getName())
                .quantity(ingredient.getQuantity())
                .coffees_with(ingredient.getCoffeesWith().stream().map(Coffee::getName).toList())
                .build();
    }

    @PostMapping
    public Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient){
        System.out.println(ingredient);
        return service.addIngredient(ingredient);
    }
    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable Long id,@RequestBody Ingredient ingredient){
        ingredient.setId(id);
        return service.updateIngredient(ingredient);
    }
    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id){
        var ingredient = service.getIngredientById(id);
        for (Coffee coffee : ingredient.getCoffeesWith()){
            coffee.getIngredients().remove(ingredient);
        }
        ingredient.getCoffeesWith().clear();
        service.deleteIngredient(ingredient);
    }


}
