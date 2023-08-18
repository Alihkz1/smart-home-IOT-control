package ir.znu.znuproject.command.heater;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.znu.znuproject.model.Heater;
import ir.znu.znuproject.shared.SWITCH;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaterChangeCommand {
    @JsonProperty("Status")
    private SWITCH Status;

    public Heater toEntity() {
        return Heater.builder().Status(Status).build();
    }
}
