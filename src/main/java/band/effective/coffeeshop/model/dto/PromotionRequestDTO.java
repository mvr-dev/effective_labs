package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PromotionRequestDTO {
    private String name;
    @JsonAlias({"coffees_id","coffeesID","coffee_id","coffeeId"})
    private List<Long> coffeesId;
    @JsonAlias({"promotion_price","price"})
    private BigDecimal promotionPrice;
}
