package ir.znu.znuproject.service;

import ir.znu.znuproject.dto.UserDTO;
import ir.znu.znuproject.entity.User;
import ir.znu.znuproject.repository.UserRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public Response register(User user) {
        Response response = new Response();
        Optional<User> existUser = userRepository.findAll().stream().filter(el -> Objects.equals(el.getUsername(), user.getUsername())).findFirst();
        if (existUser.isPresent()) {
            response.setStatus(400);
            response.setMessage("Username already taken!");
        } else {
            try {
                User savingUser = new User();
                savingUser.setUsername(user.getUsername());
                savingUser.setPassword(user.getPassword());
                savingUser.setExpireDate(LocalDate.of(LocalDate.now().getYear() + 1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
                userRepository.save(savingUser);
                response.setStatus(200);
                response.setMessage("User successfully saved!");
            } catch (Exception e) {
                response.setStatus(500);
                response.setMessage("Error occurred!");
            }
        }
        return response;
    }

    public Response login(User user) {
        Response response = new Response();
        User userExists = userRepository.login(user.getUsername(), user.getPassword());

        if (userExists != null) {
            response.setStatus(200);
            response.setMessage("successful login!");
        } else {
            response.setStatus(500);
            response.setMessage("Incorrect username or password!");
        }

        return response;
    }

    private UserDTO convertEntityToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setExpireDate(user.getExpireDate());
        return dto;
    }

}
