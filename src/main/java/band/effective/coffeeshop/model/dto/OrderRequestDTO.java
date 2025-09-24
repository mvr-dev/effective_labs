
package band.effective.coffeeshop.model.dto;

import band.effective.coffeeshop.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    @JsonAlias({"baristaID","barista_id"})
    @NonNull
    @NotEmpty
    @Positive
    private Long baristaId;

    @Nullable
    @Positive
    @JsonAlias({"customer_id","customerID",})
    private Long customerId;

    @NonNull
    @NotEmpty
    @Size(min=1,max=100)
    @JsonAlias({"coffee_id","coffees_id","coffeeId","coffeesID","coffeeID"})
    private List<Long> coffeesId;

    @Nullable
    @NotEmpty
    @Size(min = 1, max=100)
    @JsonAlias({"promo","promotion_id","promo_id"})
    private List<Long> promotions;

    @NotNull
    private OrderStatus status;

}
