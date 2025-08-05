package band.effective.coffieshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @NonNull
    @ManyToMany(mappedBy = "customerOrders")
    @JsonIgnore
    private List<Coffee> coffees;

    private OrderStatus status;
    private Double price;
}
