package band.effective.coffieshop.model.dto;

import band.effective.coffieshop.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:MM")
    private LocalDateTime time;
    public static OrderResponseDTO fromEntry(CustomerOrder order){
        return OrderResponseDTO.builder()
                .id(order.getId())
                .barista(order.getBarista())
                .customer(order.getCustomer())
                .coffees(order.getCoffees().stream().map(
                        CoffeeResponseDTO::fromEntry
                ).toList())
                .price(order.getCoffees().stream().mapToDouble(Coffee::getPrice).sum())
                .status(order.getStatus().toString())
                .time(order.getOrderTime())
                .build();
    }
}
