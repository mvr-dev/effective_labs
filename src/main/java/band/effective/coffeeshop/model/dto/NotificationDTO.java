package band.effective.coffeeshop.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationDTO {
    @JsonAlias("ids")
    private List<Long> id;
    private String subject;
    private String message;
}
