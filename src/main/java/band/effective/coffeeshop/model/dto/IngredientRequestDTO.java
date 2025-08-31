package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientRequestDTO {
    @NotEmpty
    @NonNull
    private String name;

    @NotEmpty
    @NonNull
    @Min(0)
    private Double quantity;
    @JsonAlias({"price_per_one", "cost_per_one"})

    @Min(0)
    @NonNull
    @NotEmpty
    private BigDecimal costPerOne;

}
