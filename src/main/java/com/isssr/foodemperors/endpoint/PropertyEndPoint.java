package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Property;
import com.isssr.foodemperors.service.PropertyService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Caim03 on 12/06/17.
 */

@RestController
@CrossOrigin(origins = "*")
public class PropertyEndPoint {

    @Inject
    private PropertyService propertyService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/property", method = RequestMethod.POST)
    public Property saveProperty(@RequestBody Property property, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return propertyService.saveProperty(property);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/property", method = RequestMethod.GET)
    public List<Property> searchAll(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return propertyService.searchAll();
        else{
            response.setStatus(401);
            return null;
        }

    }

    @RequestMapping(path = "api/property/findBy/name/{name}", method = RequestMethod.GET)
    public Property searchProperty(@PathVariable String name,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("warehouseman")))
            return propertyService.searchByName(name);
        else{
            response.setStatus(401);
            return null;
        }

    }

    @RequestMapping(path = "api/property/{id}", method = RequestMethod.DELETE)
    public Long deletePropertyById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        Long deleted = propertyService.deletePropertyById(id);
        if (deleted == 0){
            response.setStatus(404);
            return null;
        }
        else
            return deleted;
    }
}
