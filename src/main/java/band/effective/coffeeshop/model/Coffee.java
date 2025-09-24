package band.effective.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @NotEmpty
    @Size(max=250)
    @EqualsAndHashCode.Include
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coffee_ingredient",
            joinColumns = @JoinColumn(name = "coffee_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Ingredient> ingredients;

    @JsonIgnore
    @ManyToMany(mappedBy = "coffees")
    private Set<CustomerOrder> customerOrders;

    @JsonIgnore
    @ManyToMany(mappedBy = "promotedCoffees")
    private Set<Promotion> promotions;

    @NonNull
    @Min(0)
    @Max(100000)
    @Positive
    @EqualsAndHashCode.Include
    private BigDecimal price;

    @NonNull
    @Min(0)
    @Max(100000)
    @Positive
    @EqualsAndHashCode.Include
    private BigDecimal costPrice;


}