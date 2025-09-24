package band.effective.coffeeshop.model.dto;

import band.effective.coffeeshop.model.Coffee;
import band.effective.coffeeshop.model.Promotion;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionResponseDTO {
    private Long id;
    private List<CoffeeResponseDTO> coffees;
    private BigDecimal price;
    private BigDecimal promotion_price;
    private String name;
    private boolean available;

}
