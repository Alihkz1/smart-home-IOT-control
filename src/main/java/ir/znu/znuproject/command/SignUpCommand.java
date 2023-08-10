package ir.znu.znuproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import ir.znu.znuproject.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SignUpCommand {
    @NotNull
    @JsonProperty("username")
    private String username;
    @NotNull
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;

    public User toEntity(String pass) {
        return User.builder()
                .username(username)
                .name(name)
                .password(pass)
                .expireDate(LocalDate.of(LocalDate.now().getYear() + 1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))
                .build();
    }
}
