package band.effective.coffieshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CoffeeRequestDTO {
    private String name;
    private List<Long> ingredients;
}
