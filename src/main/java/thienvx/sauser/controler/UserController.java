package thienvx.sauser.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thienvx.sauser.entities.User;
import thienvx.sauser.repositories.UserRepository;
import thienvx.sauser.util.PasswordHashingUtil;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/register")
    @ResponseBody
    public User registerUser(@RequestBody User user) {
        java.util.Date today = new java.util.Date();
        Date createTime = new Date(today.getTime());
        Date updateTime = new Date(today.getTime());
        User newUser;
        String hashedPw;

        //Encode pw
        hashedPw = PasswordHashingUtil.pbkdf2Hash(user.getPassword().toCharArray());

        newUser = new User(user.getName(), user.getEmail(), user.getPhone(), hashedPw, createTime, updateTime);

        return userRepository.save(newUser);
    }

    @GetMapping("/auth/{id}")
    @ResponseBody
    public Optional<User> getUser(@PathVariable long id) {
        try {
            return Optional.of(userRepository.findById(id).get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @PutMapping("/auth/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        java.util.Date today = new java.util.Date();
        Date updateTime = new Date(today.getTime());
        User oldData;

        oldData = userRepository.findById(user.getId()).get();

        //Set time
        user.setPassword(oldData.getPassword());
        user.setCreateTime(oldData.getCreateTime());
        user.setUpdateTime(updateTime);

        return userRepository.save(user);
    }


}
