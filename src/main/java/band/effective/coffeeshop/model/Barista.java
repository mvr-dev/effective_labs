package band.effective.coffeeshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Barista {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotEmpty
    @Size(max=250)
    private String surname;
    @NonNull
    @NotEmpty
    @Size(max=250)
    private String name;
}
