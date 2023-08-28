package ir.znu.znuproject.command.Temperature;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.znu.znuproject.model.TemperatureGoal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureChangeGoalCommand {
    @JsonProperty("goal")
    private Integer goal;

   public TemperatureGoal toEntity() {
        return TemperatureGoal.builder().goal(goal).build();
    }
}
