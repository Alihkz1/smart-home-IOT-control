package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.UserCommand;
import ir.znu.znuproject.dto.UserDTO;
import ir.znu.znuproject.model.User;
import ir.znu.znuproject.service.UserService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<Map<String, List<UserDTO>>>> getUserList() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<String>> signup(@RequestBody(required = true) UserCommand command) {
        return userService.signup(command);
    }

    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response<String>> login(@RequestBody(required = true) UserCommand command) {
        return userService.login(command);
    }
}
