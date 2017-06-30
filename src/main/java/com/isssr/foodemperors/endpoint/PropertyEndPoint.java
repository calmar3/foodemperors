package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Property;
import com.isssr.foodemperors.service.PropertyService;
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

    @RequestMapping(path = "api/property", method = RequestMethod.POST)
    public Property saveProperty(@RequestBody Property property) {
        return propertyService.saveProperty(property);
    }

    @RequestMapping(path = "api/property", method = RequestMethod.GET)
    public List<Property> searchAll() {
        return propertyService.searchAll();
    }

    @RequestMapping(path = "api/property/findBy/name/{name}", method = RequestMethod.GET)
    public Property searchProperty(@PathVariable String name) {
        return propertyService.searchByName(name);
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
