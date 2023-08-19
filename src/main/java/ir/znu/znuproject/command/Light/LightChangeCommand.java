package ir.znu.znuproject.command.Light;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.znu.znuproject.model.Light;
import ir.znu.znuproject.shared.SWITCH;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightChangeCommand {
    @JsonProperty("Status")
    private SWITCH Status;

    @JsonProperty("Intensity")
    private String Intensity = "";

    public Light toEntity() {
        return Light.builder().Status(Status).Intensity(Intensity).build();
    }
}
