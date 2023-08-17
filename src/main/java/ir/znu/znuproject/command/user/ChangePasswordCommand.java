package ir.znu.znuproject.command.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import ir.znu.znuproject.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChangePasswordCommand {
    @NotNull
    @JsonProperty("ID")
    private Long ID;
    @NotNull
    @JsonProperty("password")
    private String password;

    public User toEntity(String pass) {
        return User.builder()
                .ID(ID)
                .password(pass)
                .build();
    }
}
