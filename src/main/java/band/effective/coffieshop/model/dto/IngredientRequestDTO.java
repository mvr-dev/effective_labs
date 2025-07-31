package band.effective.coffieshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientRequestDTO {
    private String name;
    private Double quantity;
//just a comm
}
