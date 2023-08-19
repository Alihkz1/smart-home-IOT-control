package ir.znu.znuproject.command.Temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureChangeCommand {
    @JsonProperty("Temperature")
    private String Temperature;

    public ir.znu.znuproject.model.Temperature toEntity() {
        return ir.znu.znuproject.model.Temperature.builder().build();
    }
}
