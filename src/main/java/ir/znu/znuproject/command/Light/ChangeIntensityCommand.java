package ir.znu.znuproject.command.Light;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeIntensityCommand {
    private Integer Intensity;
}
