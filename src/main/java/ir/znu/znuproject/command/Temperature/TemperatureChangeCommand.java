package ir.znu.znuproject.command.Temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureChangeCommand {
    @NotNull
    @JsonProperty("Temperature")
    private String Temperature;
}
