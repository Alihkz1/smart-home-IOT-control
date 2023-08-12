package ir.znu.znuproject.service;

import ir.znu.znuproject.command.LoginCommand;
import ir.znu.znuproject.command.SignUpCommand;
import ir.znu.znuproject.dto.LoginDto;
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
<<<<<<< Updated upstream
            map.put("users", users);
            map.put("length", users.size()); /*todo : create userListDto that includes rowCount and list*/
            response.setData(map);
            response.setSuccess(true);
=======
            UserListDto userListDto = UserListDto.builder()
                    .users(users)
                    .rowCount(users.size())
                    .build();
            response.setData(userListDto);
>>>>>>> Stashed changes
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response<String>> signup(SignUpCommand user) {
        Response response = new Response();
        if (user.getName() == null)
            throw new IllegalArgumentException("name is required.");
        Optional<User> existUser = userRepository.findAll().stream()
                .filter(el -> el.getUsername().equals(user.getUsername())).findFirst();
        if (existUser.isPresent()) {
            response.setSuccess(false);
            response.setMessage("Email Already Taken");
            return ResponseEntity.badRequest().body(response);
        } else {
            try {
                userRepository.save(user.toEntity(passwordEncoder.encode(user.getPassword())));
                response.setMessage("User successfully registered!");
                return ResponseEntity.ok().body(response);
            } catch (Exception e) {
                response.setMessage("Internal Server Error!");
                return ResponseEntity.internalServerError().body(response);
            }
        }
    }

    public ResponseEntity<Response> login(LoginCommand user) {
        Response response = new Response();
        User existUser = userRepository.login(user.getUsername());
        if (existUser == null) {
            response.setSuccess(false);
            response.setMessage("Incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), existUser.getPassword());
        if (existUser != null && passwordMatches) {
            Optional<UserDTO> responseUser = userRepository.findAll().stream()
                    .filter(el -> el.getUsername().equals(user.getUsername()))
                    .map(userDtoMapper).findFirst();
            var token = jwtService.generateToken(existUser);
            LoginDto loginDto = LoginDto.builder()
                    .token(token)
                    .user(responseUser)
                    .build();
            response.setMessage("successful login!");
            response.setData(loginDto);
            return ResponseEntity.ok().body(response);
        } else {
            response.setSuccess(false);
            response.setMessage("Incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
    }


}
