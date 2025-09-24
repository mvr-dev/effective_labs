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
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @NonNull
    @Size(max = 250)
    private String name;
    @NotEmpty
    @NonNull
    @Size(max = 250)
    private String surname;

    @Email(message = "non valid email")
    @NonNull
    @NotEmpty
    private String email;

    @NotEmpty
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotEmpty
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
