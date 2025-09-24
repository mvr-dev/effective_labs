package band.effective.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coffee_promotion",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id"))
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Coffee> promotedCoffees;

    @NonNull
    @Min(0)
    private BigDecimal promotionPrice;

    @ManyToMany(mappedBy = "promotions")
    private Set<CustomerOrder> orders;
    private boolean available = true;



}
