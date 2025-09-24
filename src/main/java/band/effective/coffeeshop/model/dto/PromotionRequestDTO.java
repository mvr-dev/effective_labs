package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PromotionRequestDTO {
    private String name;
    @JsonAlias({"coffees_id","coffeesID","coffee_id","coffeeId"})
    private List<Long> coffeesId;
    @JsonAlias({"promotion_price","price"})
    private BigDecimal promotionPrice;
}
