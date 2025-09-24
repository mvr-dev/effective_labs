package band.effective.coffeeshop.controller;


import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.IngredientRequestDTO;
import band.effective.coffeeshop.model.dto.IngredientResponseDTO;
import band.effective.coffeeshop.service.IIngredientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
public class IngredientController {
    private final IIngredientService service;

    @GetMapping
    public Page<IngredientResponseDTO> getAllIngredients(@RequestParam(value = "offset",defaultValue = "0") int pageNumber,
                                                         @RequestParam(value = "limit", defaultValue = "20") int pageSize){
        return service.getAllIngredients(pageNumber, pageSize);
    }
    @GetMapping("/{id}")
    public IngredientResponseDTO getIngredientById(@PathVariable Long id){
        return service.getIngredientById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect id"
        ));
    }

    @PostMapping
    public IngredientResponseDTO addIngredient(@Valid @RequestBody IngredientRequestDTO ingredient){
        return service.addIngredient(ingredient);
    }
    @PutMapping("/{id}")
    public IngredientResponseDTO updateIngredient(@PathVariable long id,@RequestBody IngredientRequestDTO ingredient){
        return service.updateIngredient(id,ingredient);
    }
    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id){
        service.deleteIngredient(id);
    }


}
