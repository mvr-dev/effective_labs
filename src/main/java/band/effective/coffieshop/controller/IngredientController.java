package band.effective.coffieshop.controller;

import band.effective.coffieshop.model.Ingredient;
import band.effective.coffieshop.service.IIngredientService;
import band.effective.coffieshop.service.impl.IngredientService;
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
    public List<Ingredient> getAllIngredients(){
        return service.getAllIngredients();
    }
    @GetMapping("/stock")
    public List<Ingredient> getAllIngredientsInStock(){
        return service.getAllIngredientsInStock();
    }
    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable Long id){
        return service.getIngredientById(id);
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
        service.deleteIngredient(service.getIngredientById(id));
    }


}
