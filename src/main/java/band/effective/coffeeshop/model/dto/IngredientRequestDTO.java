package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientRequestDTO {
    private String name;
    private BigDecimal quantity;
    @JsonAlias({"price_per_one", "cost_per_one"})
    private BigDecimal costPerOne;

}
