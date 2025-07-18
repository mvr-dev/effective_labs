package band.effective.coffieshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//b
@Data
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
    private String surname;
    @NonNull
    @NotEmpty
    private String name;
}
