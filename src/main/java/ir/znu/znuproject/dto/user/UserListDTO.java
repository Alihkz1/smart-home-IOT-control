package ir.znu.znuproject.dto.user;

import ir.znu.znuproject.dto.user.UserDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class UserListDTO {
    private List<UserDTO> users;
    private Integer rowCount;

}
