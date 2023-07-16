package ir.znu.znuproject.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {
    @JsonProperty("username")
    private String username;

    public UserModel() {
    }

    public UserModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
