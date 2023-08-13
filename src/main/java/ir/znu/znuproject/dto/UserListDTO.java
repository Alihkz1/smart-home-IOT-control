package ir.znu.znuproject.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class UserListDTO {
    private List<UserDTO> users;
    private Integer rowCount;

}
