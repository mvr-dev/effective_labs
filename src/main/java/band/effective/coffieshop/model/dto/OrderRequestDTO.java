
package band.effective.coffieshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Long baristaId;
    private Long customerId;
    private List<Long> coffeesId;
    private Integer status;
}
