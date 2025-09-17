package band.effective.coffeeshop.service.mapper;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import band.effective.coffeeshop.model.dto.CoffeeRequestDTO;
import band.effective.coffeeshop.model.dto.CoffeeResponseDTO;
import band.effective.coffeeshop.service.IIngredientService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CoffeeMapper {
    private IIngredientService ingredientService;
    public CoffeeResponseDTO fromEntry(Coffee coffee){
        return CoffeeResponseDTO.builder()
                .ingredients(coffee.getIngredients().stream().map(Ingredient::getName).toList())
                .name(coffee.getName())
                .id(coffee.getId())
                .cost_price(coffee.getCostPrice())
                .price(coffee.getPrice())
                .build();
    }
    public Coffee toEntry(CoffeeRequestDTO coffeeRequestDTO){

        Set<Ingredient> ingredients = new HashSet<>(ingredientService.findAllById(coffeeRequestDTO.getIngredients()));
        return Coffee.builder()
                .name(coffeeRequestDTO.getName())
                .ingredients(ingredients)
                .price(coffeeRequestDTO.getPrice())
                .costPrice(ingredients.stream().map(Ingredient::getCostPerOne).reduce(new BigDecimal("0.0"),(x, y)->x.add(y)))
                .build();
    }
}
