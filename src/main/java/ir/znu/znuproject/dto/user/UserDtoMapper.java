package ir.znu.znuproject.dto.user;

import ir.znu.znuproject.dto.user.UserDTO;
import ir.znu.znuproject.model.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getID(),
                user.getUsername(),
                user.getExpireDate(),
                user.getName(),
                user.getRole()
        );
    }

}
