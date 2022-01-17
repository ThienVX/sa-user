package thienvx.sauser.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import thienvx.sauser.entities.User;
import thienvx.sauser.repositories.UserRepository;

import java.util.Date;
import java.util.Map;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> registerUser(String name, String email, String password) {
        Date createDate, updateDate = new Date();
        User newUser;

        newUser = new User(name, email, password, )

        userRepository.save()
    }
}
