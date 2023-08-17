package ir.znu.znuproject.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserCommand {
    @JsonProperty("username")
    String username;
}
