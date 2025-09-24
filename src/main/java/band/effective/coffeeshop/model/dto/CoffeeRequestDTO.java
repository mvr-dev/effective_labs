package band.effective.coffeeshop.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CoffeeRequestDTO {
    private String name;
    private List<Long> ingredients;
    private BigDecimal price;
}
