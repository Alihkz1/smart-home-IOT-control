package ir.znu.znuproject.controller;

import ir.znu.znuproject.dto.UserDTO;
import ir.znu.znuproject.entity.User;
import ir.znu.znuproject.service.UserService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "list")
    ResponseEntity<Response<Map<String, List<UserDTO>>>> getUserList() {
        return userService.findAll();
    }

    @PostMapping(path = "register")
    ResponseEntity<Response<String>>  register(@RequestBody(required = true) User user) {
        return userService.register(user);
    }

    @PostMapping(path = "login")
    ResponseEntity<Response<String>> login(@RequestBody(required = true) User model) {
        return userService.login(model);
    }
}
