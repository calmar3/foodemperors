package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.User;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.service.UserService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/user", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(path = "api/user/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        User found = userService.login(user.getUsername(), user.getPassword());
        if (found == null) {
            response.setStatus(404);
            return new String("User Not Found");
        } else
            return tokenService.generateToken(found.getUsername(),found.getRole());

    }

    @RequestMapping(path = "api/user", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload.getRole().equals("admin"))
            return userService.updateUser(user);
        else{
            response.setStatus(401);
            return null;
        }
    }

}
