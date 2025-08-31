package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PromotionRequestDTO {
    @NonNull
    @NotEmpty
    private String name;
    @JsonAlias({"coffees_id","coffeesID","coffee_id","coffeeId"})
    @NonNull
    @NotEmpty
    private List<Long> coffeesId;
    @JsonAlias({"promotion_price","price"})
    @NonNull
    @NotEmpty
    @Min(0)
    private BigDecimal promotionPrice;
}
