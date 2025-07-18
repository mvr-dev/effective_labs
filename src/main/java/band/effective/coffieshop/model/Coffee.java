package band.effective.coffieshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)

//    @CollectionTable(
//            name = "coffee_ingredient",
//            joinColumns = @JoinColumn(name = "ingredient_id")
//    )
    @JoinTable(name = "coffee_ingredient",
            joinColumns = @JoinColumn(name = "coffee_id"), // исправлено
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

}