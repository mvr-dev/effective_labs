package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientRequestDTO {
    @NotEmpty
    @NonNull
    @Size(max = 250)
    private String name;

    @NonNull
    @Min(0)
    private BigDecimal quantity;
  
    @JsonAlias({"price_per_one", "cost_per_one"})
    @Min(0)
    @NonNull
    private BigDecimal costPerOne;

}
