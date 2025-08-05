package band.effective.coffieshop.model.dto;

import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeResponseDTO {
    private Long id;
    private String name;
    private List<String> ingredients;
    private Double cost_price;
    private Double price;
    public static CoffeeResponseDTO fromEntry(Coffee coffee){
        return CoffeeResponseDTO.builder()
                .ingredients(coffee.getIngredients().stream().map(Ingredient::getName).toList())
                .name(coffee.getName())
                .id(coffee.getId())
                .cost_price(coffee.getCostPrice())
                .price(coffee.getPrice())
                .build();
    }
}
