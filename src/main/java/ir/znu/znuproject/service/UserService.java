package ir.znu.znuproject.service;

import ir.znu.znuproject.entity.User;
import ir.znu.znuproject.repository.UserRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response<User> findAll() {
        Response response = new Response();
        Map map = new HashMap<String, List<User>>();

        try {
            map.put("users", userRepository.findAll());
            response.setData(map);
            response.setStatus(200);
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage("Error occurred!");
        }

        return response;
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


}
