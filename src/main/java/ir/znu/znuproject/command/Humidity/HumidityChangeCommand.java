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


    public ir.znu.znuproject.model.Humidity toEntity() {
        return ir.znu.znuproject.model.Humidity.builder()
                .Humidity(Humidity)
                .build();
    }
}
