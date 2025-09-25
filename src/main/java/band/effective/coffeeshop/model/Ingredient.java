package band.effective.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 0)
    private BigDecimal quantity;
  
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Coffee> coffeesWith;

    @NotNull
    @Min(0)
    @JsonAlias({"price_per_one", "cost_per_one"})
    private BigDecimal costPerOne;


}
