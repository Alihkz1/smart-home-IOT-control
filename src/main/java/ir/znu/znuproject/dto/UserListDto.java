package ir.znu.znuproject.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class UserListDto {
    private List<UserDTO> users;
    private Integer rowCount;

}
