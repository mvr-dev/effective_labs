package band.effective.coffeeshop.model.dto;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeResponseDTO {
    private Long id;
    private String name;
    private List<String> ingredients;
    private BigDecimal cost_price;
    private BigDecimal price;
}
