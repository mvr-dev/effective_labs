
package band.effective.coffeeshop.model.dto;

import band.effective.coffeeshop.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

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
    @JsonAlias({"promo","promotion_id","promo_id"})
    private List<Long> promotions;
    private OrderStatus status;
}
