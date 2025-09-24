package band.effective.coffeeshop.model.dto;

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
public class IngredientResponseDTO {
    private Long id;
    private String name;
    private BigDecimal quantity;
    private List<String> coffees_with;
    private BigDecimal cost_per_one;
}
