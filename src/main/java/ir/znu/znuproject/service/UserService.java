package ir.znu.znuproject.service;

import ir.znu.znuproject.command.user.*;
import ir.znu.znuproject.dto.user.LoginDTO;
import ir.znu.znuproject.dto.user.UserDTO;
import ir.znu.znuproject.dto.user.UserDtoMapper;
import ir.znu.znuproject.dto.user.UserListDTO;
import ir.znu.znuproject.model.User;
import ir.znu.znuproject.repository.UserRepository;
import ir.znu.znuproject.shared.JWTService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;
    private final Response response;


    @Autowired
    public UserService(UserRepository userRepository,
                       JWTService jwtService,
                       PasswordEncoder passwordEncoder,
                       UserDtoMapper userDtoMapper,
                       Response response
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userDtoMapper = userDtoMapper;
        this.response = response;
    }

    public ResponseEntity<Response<UserListDTO>> getAllUsers(Integer PageIndex, Integer PageSize) {
        try {
            Pageable pageRequest = PageRequest.of(PageIndex, PageSize);
            List<UserDTO> users = userRepository.findAll(pageRequest).stream().map(userDtoMapper).collect(Collectors.toList());
            UserListDTO userListDto = UserListDTO.builder()
                    .users(users)
                    .rowCount(users.size())
                    .build();
            response.setData(userListDto);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> signup(SignUpCommand user) {
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
        User existUser = userRepository.login(user.getUsername());
        if (existUser == null) {
            response.setSuccess(false);
            response.setMessage("incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), existUser.getPassword());
        if (existUser != null && passwordMatches) {
            Optional<UserDTO> responseUser = userRepository.findAll().stream()
                    .filter(el -> el.getUsername().equals(user.getUsername()))
                    .map(userDtoMapper).findFirst();
            var token = jwtService.generateToken(existUser);
            LoginDTO loginDto = LoginDTO.builder()
                    .token(token)
                    .user(responseUser)
                    .build();
            response.setMessage("successful login!");
            response.setData(loginDto);
            return ResponseEntity.ok().body(response);
        } else {
            response.setSuccess(false);
            response.setMessage("incorrect username or password!");
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<Response> changePassword(ChangePasswordCommand command) {
        userRepository.changePassword(command.getID(), passwordEncoder.encode(command.getPassword()));
        response.setMessage("password changed.");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> deleteAll() {
        userRepository.deleteAll();
        response.setMessage("users list cleared.");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> deleteByUsername(DeleteUserCommand command) {
        userRepository.deleteByUsername(command.getUsername());
        response.setMessage(String.format("User %s deleted.", command.getUsername()));
        return ResponseEntity.ok(response);
    }

}
