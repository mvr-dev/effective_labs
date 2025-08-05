package band.effective.coffieshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PromotionRequestDTO {
    private String name;
    @JsonAlias({"coffees_id","coffeesID"})
    private List<Long> coffeesId;
    @JsonAlias({"promotion_price","price"})
    private Double promotionPrice;
}
