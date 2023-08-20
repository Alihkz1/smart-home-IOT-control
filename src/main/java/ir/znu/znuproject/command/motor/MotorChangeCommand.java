package ir.znu.znuproject.command.motor;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.znu.znuproject.model.Motor;
import ir.znu.znuproject.enums.SWITCH;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorChangeCommand {
    @JsonProperty("Status")
    private SWITCH Status;

    public Motor toEntity() {
        return Motor.builder().Status(Status).build();
    }
}
