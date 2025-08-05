
package band.effective.coffieshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonAlias({"baristaID","barista_id"})
    private Long baristaId;
    @JsonAlias({"customer_id","customerID",})
    private Long customerId;
    @JsonAlias({"coffee_id","coffees_id","coffeeId","coffeesID","coffeeID"})
    private List<Long> coffeesId;
    private Integer status;
}
