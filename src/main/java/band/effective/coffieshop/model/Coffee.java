package band.effective.coffieshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.lang.annotation.Target;
import java.util.Map;
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
    private String name;

    @ManyToMany
    @CollectionTable(
            name = "coffee_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JoinTable(name = "coffee_ingredient",
            joinColumns = @JoinColumn(name = "ingredient_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id",referencedColumnName = "id"))

    private Set<Ingredient> ingredients;
}