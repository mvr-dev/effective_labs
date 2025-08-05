package band.effective.coffieshop.model.dto;

import band.effective.coffieshop.model.Barista;
import band.effective.coffieshop.model.Coffee;
import band.effective.coffieshop.model.Customer;
import band.effective.coffieshop.model.CustomerOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Barista barista;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
    private Double price;
    private List<CoffeeResponseDTO> coffees;

    public static OrderResponseDTO fromEntry(CustomerOrder order){
        return OrderResponseDTO.builder()
                .id(order.getId())
                .barista(order.getBarista())
                .customer(order.getCustomer())
                .coffees(order.getCoffees().stream().map(
                        CoffeeResponseDTO::fromEntry
                ).toList())
                .price(order.getCoffees().stream().mapToDouble(Coffee::getPrice).sum())
                .build();
    }
}
