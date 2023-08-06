package ir.znu.znuproject.service;

import ir.znu.znuproject.command.UserCommand;
import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.dto.UserDTO;
import ir.znu.znuproject.dto.UserDtoMapper;
import ir.znu.znuproject.model.User;
import ir.znu.znuproject.repository.UserRepository;
import ir.znu.znuproject.shared.JWTService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       JWTService jwtService,
                       PasswordEncoder passwordEncoder,
                       UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userDtoMapper = userDtoMapper;
    }

    public ResponseEntity<Response> getAllUsers() {
        Response response = new Response();
        Map map = new HashMap<String, List<UserDTO>>();
        try {
            List<UserDTO> users = userRepository.findAll().stream().map(userDtoMapper
            ).collect(Collectors.toList());
            map.put("users", users);
            map.put("length", users.size());
            response.setData(map);
            response.setSuccess(true);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response<String>> signup(UserCommand user) {
        Response response = new Response();
        Optional<User> existUser = userRepository.findAll().stream().filter(el -> Objects.equals(el.getUsername(), user.getUsername())).findFirst();
        if (existUser.isPresent()) {
            response.setSuccess(false);
            response.setMessage("Email Already Taken");
            return ResponseEntity.badRequest().body(response);
        } else {
            try {
                User savingUser = new User();
                savingUser.setUsername(user.getUsername());
                savingUser.setName(user.getName());
                savingUser.setPassword(passwordEncoder.encode(user.getPassword()));
                savingUser.setExpireDate(LocalDate.of(LocalDate.now().getYear() + 1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
                userRepository.save(savingUser);
                response.setSuccess(true);
                response.setMessage("User successfully registered!");
                return ResponseEntity.ok().body(response);
            } catch (Exception e) {
                response.setMessage("Internal Server Error!");
                return ResponseEntity.internalServerError().body(response);
            }
        }
    }

    public ResponseEntity<Response> login(UserCommand user) {
        Response response = new Response();
        User existUser = userRepository.login(user.getUsername());
        if (existUser == null) {
            response.setSuccess(false);
            response.setMessage("Incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), existUser.getPassword());
        if (existUser != null && passwordMatches) {
            Optional<UserDTO> responseUser = userRepository.findAll().stream().filter(el -> Objects.equals(el.getUsername(), user.getUsername())).map(userDtoMapper).findFirst();
            var token = jwtService.generateToken(existUser);
            Map map = new HashMap<String, String>();
            map.put("token", token);
            map.put("user", responseUser);
            response.setSuccess(true);
            response.setMessage("successful login!");
            response.setData(map);
            return ResponseEntity.ok().body(response);
        } else {
            response.setSuccess(false);
            response.setMessage("Incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
    }


}
