package endpoint;

import model.User;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by marco on 19/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class UserEndpoint {

    @Inject
    private UserRepository userRepository;

    @RequestMapping(path = "api/user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(path = "api/user/login", method = RequestMethod.POST)
    public Object Login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        User found = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if (found == null){
            response.setStatus(404);
            return new String("User Not Found");
        }
        else
            return found;
    }



}
