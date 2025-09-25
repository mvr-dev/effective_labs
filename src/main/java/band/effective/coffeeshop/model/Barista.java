package band.effective.coffeeshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
    @Size(max=250)
    private String surname;

    @NotNull
    @NotEmpty
    @Size(max=250)
    private String name;
}
