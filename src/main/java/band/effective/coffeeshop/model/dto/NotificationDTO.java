package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationDTO {
    @JsonAlias("ids")
    private List<Long> id;
    @NonNull
    private String subject;
    @NonNull
    private String message;
}
