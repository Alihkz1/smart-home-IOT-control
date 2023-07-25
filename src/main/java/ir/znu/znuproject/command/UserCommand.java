package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommand {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
