package band.effective.coffieshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

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
    private Set<Coffee> coffeesWith;

}
