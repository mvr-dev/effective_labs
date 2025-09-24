package band.effective.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
//many-to-many problem
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @NotEmpty
    @EqualsAndHashCode.Include
    private String name;

    @NonNull
    @Min(value = 0)
    @EqualsAndHashCode.Include
    private BigDecimal quantity;
  
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Coffee> coffeesWith;

    @NonNull
    @Min(0)
    @JsonAlias({"price_per_one", "cost_per_one"})
    @EqualsAndHashCode.Include
    private BigDecimal costPerOne;


}
