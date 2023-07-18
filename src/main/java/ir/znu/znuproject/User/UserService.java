package ir.znu.znuproject.User;

import ir.znu.znuproject.shared.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Response saveUser(User user) {
        /*todo : check duplicate username*/
        Response response = new Response();

        try {
            userRepository.save(user);
            response.setStatus(200);
            response.setMessage("User successfully saved!");
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage("Error occurred!");
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
