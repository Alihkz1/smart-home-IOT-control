package ir.znu.znuproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "list")
    Response<User> getUserList() {
        return userService.findAll();
    }

    @PostMapping(path = "save")
    Response saveUser(@RequestBody(required = true) User user) {
        return userService.saveUser(user);
    }

    @PostMapping(path = "login")
    Response login(@RequestBody(required = true) User model) {
        return userService.login(model.getUsername());
    }
}
