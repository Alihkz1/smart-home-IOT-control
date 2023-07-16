package ir.znu.znuproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> getUserList() {
        return userService.findAll();
    }

    @PostMapping
    Boolean saveUser(User user) {
        return userService.saveUser(user);
    }

    @PostMapping
    Boolean login(User user) {
        return userService.login(user);
    }
}
