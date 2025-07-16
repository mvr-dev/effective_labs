package band.effective.coffieshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Barista {
    @Id
    @GeneratedValue
    private Long id;
    private String surname;
    private String name;
}
