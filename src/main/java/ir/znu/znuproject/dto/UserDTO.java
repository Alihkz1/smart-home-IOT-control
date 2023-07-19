package ir.znu.znuproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String username;
    private String password;
    private LocalDate expireDate;
}
