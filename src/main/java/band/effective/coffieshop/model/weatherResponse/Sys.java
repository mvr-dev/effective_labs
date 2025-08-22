package band.effective.coffieshop.model.weatherResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sys {
    @JsonProperty("type")
    private Integer type;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("country")
    private String country;

    @JsonProperty("sunrise")
    private Long sunrise;

    @JsonProperty("sunset")
    private Long sunset;
}
