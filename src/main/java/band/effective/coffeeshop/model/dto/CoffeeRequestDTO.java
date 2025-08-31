package band.effective.coffeeshop.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CoffeeRequestDTO {
    @NotEmpty
    @NonNull
    private String name;
    @NotEmpty
    @NonNull
    private List<Long> ingredients;
    @NotEmpty
    @NonNull
    @Positive
    @Min(0)
    private BigDecimal price;
}
