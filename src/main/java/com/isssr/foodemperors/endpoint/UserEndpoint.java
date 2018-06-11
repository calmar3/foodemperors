package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.dto.UserDTO;
import com.isssr.foodemperors.model.User;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.service.UserService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public User saveUser(@RequestBody User user,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && tokenPayload.getRole().equals("admin"))
            return userService.saveUser(user);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/user/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        User found = userService.login(user.getUsername(), user.getPassword());
        if (found == null) {
            response.setStatus(404);
            return new UserDTO(null,"user not found");
        } else {
            UserDTO userDTO = new UserDTO(found, tokenService.generateToken(found.getUsername(), found.getRole()));
            return userDTO;
        }
    }

    @RequestMapping(path = "api/user", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null)
            return userService.updateUser(user);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/user/{id}", method = RequestMethod.DELETE)
    public Long deleteUser(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && tokenPayload.getRole().equals("admin"))
            return userService.deleteUser(id);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/user", method = RequestMethod.GET)
    public List<User> findAll(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && tokenPayload.getRole().equals("admin"))
            return userService.findAll();
        else {
            response.setStatus(401);
            return null;
        }
    }
}
