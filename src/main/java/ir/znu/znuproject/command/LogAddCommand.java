package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import ir.znu.znuproject.model.Log;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogAddCommand {
    @NotNull
    @JsonProperty("content")
    private String content;

    public Log toEntity(){
        return Log.builder()
                .content(content)
                .build();
    }
}
