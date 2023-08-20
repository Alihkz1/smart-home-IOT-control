package ir.znu.znuproject.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class BaseCommand {
    @JsonProperty(value = "PageSize")
    @Positive
    @NotNull
    @Max(100)
    private Integer PageSize = 10;

    @JsonProperty(value = "PageIndex")
    @PositiveOrZero
    @NotNull
    private Integer PageIndex = 0;
}
