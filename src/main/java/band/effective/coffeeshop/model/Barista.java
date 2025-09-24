package band.effective.coffeeshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Barista {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    @NotEmpty
    @Size(max=250)
    @EqualsAndHashCode.Include
    private String surname;
    @NonNull
    @NotEmpty
    @Size(max=250)
    @EqualsAndHashCode.Include
    private String name;
}
