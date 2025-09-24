package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationDTO {
    @JsonAlias("ids")
    @Positive
    @NonNull
    @NotEmpty
    @Size(min=1, max=25)
    private List<Long> id;
    @NonNull
    @NotEmpty
    @Size(max = 500)
    private String subject;
    @NonNull
    @NotEmpty
    @Size(max = 2000)
    private String message;
}
