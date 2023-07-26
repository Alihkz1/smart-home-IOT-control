package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommand {
    @NotNull
    @JsonProperty("username")
    private String username;
    @NotNull
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
}
