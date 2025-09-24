package band.effective.coffeeshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty
    @NonNull
    @Size(max = 250)
    @EqualsAndHashCode.Include
    private String name;

    @NotEmpty
    @NonNull
    @Size(max = 250)
    @EqualsAndHashCode.Include
    private String surname;

    @Email(message = "non valid email")
    @NonNull
    @NotEmpty
    @EqualsAndHashCode.Include
    private String email;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @EqualsAndHashCode.Include
    private LocalDate birthday;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastOrder;

    @JsonIgnore
    @Column(precision = 19, scale = 2, columnDefinition = "numeric(19,2) default 0.00")
    private BigDecimal points = BigDecimal.ZERO;

    @JsonIgnore
    @Column(precision = 19, scale = 2, columnDefinition = "numeric(19,2) default 0.00")
    private BigDecimal weaklyPoints = BigDecimal.ZERO;

}
