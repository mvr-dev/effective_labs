package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PromotionRequestDTO {
    @NonNull
    @NotEmpty
    @Size(max=250)
    private String name;

    @JsonAlias({"coffees_id","coffeesID","coffee_id","coffeeId"})
    @NonNull
    @NotEmpty
    @Size(max=100)
    private List<Long> coffeesId;

    @JsonAlias({"promotion_price","price"})
    @NonNull
    @NotEmpty
    @Min(0)
    @Positive
    private BigDecimal promotionPrice;
}
