package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogAddCommand {
    @NotNull
    @JsonProperty("content")
    private String content;
}
