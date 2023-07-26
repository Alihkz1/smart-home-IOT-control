package ir.znu.znuproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import ir.znu.znuproject.enums.Role;
import java.time.LocalDate;

public record UserDTO(
        String username,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate expireDate,
        String name,
        Role role
) {
}
