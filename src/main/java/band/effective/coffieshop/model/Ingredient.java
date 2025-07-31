package band.effective.coffieshop.model;

import band.effective.coffieshop.repository.IngredientRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;
//many-to-many problem
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @NotEmpty
    private String name;
    @NonNull
    @Min(value = 0)
    private Double quantity;
    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Coffee> coffeesWith;


}
