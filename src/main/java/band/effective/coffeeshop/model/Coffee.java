package band.effective.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotEmpty
    @Size(max=250)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
    private Double price;

    @NonNull
    @Min(0)
    @Max(100000)
    @Positive
    private Double costPrice;

}