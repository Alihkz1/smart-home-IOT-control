package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.user.*;
import ir.znu.znuproject.dto.user.UserListDTO;
import ir.znu.znuproject.service.UserService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Response<UserListDTO>> list(@RequestParam(required = false) Integer PageIndex, @RequestParam(required = false) Integer PageSize) {
        return userService.getAllUsers(PageIndex, PageSize);
    }

    @PostMapping(path = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> signup(@RequestBody(required = true) SignUpCommand command) {
        return userService.signup(command);
    }

    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> login(@RequestBody(required = true) LoginCommand command) {
        return userService.login(command);
    }

    @PutMapping(path = "changepassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> changePassword(@RequestBody(required = true) ChangePasswordCommand command) {
        return userService.changePassword(command);
    }

    @DeleteMapping(path = "deleteall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll() {
        return userService.deleteAll();
    }

    @DeleteMapping(path = "deleteuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteByUsername(@RequestBody(required = true) DeleteUserCommand command) {
        return userService.deleteByUsername(command);
    }
}
