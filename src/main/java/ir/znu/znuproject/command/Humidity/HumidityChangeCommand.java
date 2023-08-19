package ir.znu.znuproject.command.Humidity;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.znu.znuproject.model.Humidity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumidityChangeCommand {
    @JsonProperty("Humidity")
    private String Humidity;

}
