package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.User;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.UserRepository;

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

    @RequestMapping(path = "api/user/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) {
        /* La funzione save inserisce un elemento se non esiste, altrimenti lo aggiorna */
        return userRepository.save(user);
    }
}
