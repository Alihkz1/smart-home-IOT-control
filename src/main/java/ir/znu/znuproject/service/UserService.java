package ir.znu.znuproject.service;

import ir.znu.znuproject.dto.UserDTO;
import ir.znu.znuproject.entity.User;
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

    @Autowired
    public UserService(UserRepository userRepository,
                       JWTService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Response<Map<String, UserDTO>>> findAll() {
        Response response = new Response();
        Map map = new HashMap<String, List<UserDTO>>();

        try {
            List<UserDTO> users = userRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
            map.put("users", users);
            response.setData(map);
            response.setStatus(200);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setStatus(500);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response<String>> register(User user) {
        Response<String> response = new Response();
        Optional<User> existUser = userRepository.findAll().stream().filter(el -> Objects.equals(el.getUsername(), user.getUsername())).findFirst();
        if (existUser.isPresent()) {
            response.setStatus(400);
            response.setMessage("Email Already Taken");
            return ResponseEntity.badRequest().body(response);
        } else {
            try {
                User savingUser = new User();
                savingUser.setUsername(user.getUsername());
                savingUser.setPassword(user.getPassword());
                savingUser.setExpireDate(LocalDate.of(LocalDate.now().getYear() + 1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
                userRepository.save(savingUser);
                response.setStatus(200);
                response.setMessage("User successfully saved!");
                return ResponseEntity.ok().body(response);
            } catch (Exception e) {
                response.setMessage("Internal Server Error!");
                return ResponseEntity.internalServerError().body(response);
            }
        }
    }

    public ResponseEntity<Response<String>> login(User user) {
        Response response = new Response();
        User userExists = userRepository.login(user.getUsername(), passwordEncoder.encode(user.getPassword()));

        if (userExists != null) {
            var token = jwtService.generateToken(user);
            Map map = new HashMap<String, String>();
            map.put("token", token);
            response.setStatus(200);
            response.setMessage("successful login!");
            response.setData(map);
            return ResponseEntity.ok().body(response);
        } else {
            response.setStatus(400);
            response.setMessage("Incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    private UserDTO convertEntityToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setExpireDate(user.getExpireDate());
        return dto;
    }

}
