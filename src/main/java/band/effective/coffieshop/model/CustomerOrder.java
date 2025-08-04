package band.effective.coffieshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @ManyToOne
    private Barista barista;

    @ManyToOne
    private Customer customer;

    @NonNull
    @ManyToMany(mappedBy = "customerOrders")
    private List<Coffee> coffees;

    private OrderStatus status;
    private Double price;
}
