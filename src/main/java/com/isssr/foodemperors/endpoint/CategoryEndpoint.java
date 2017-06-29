package com.isssr.foodemperors.endpoint;


import com.isssr.foodemperors.dto.CategoryDTO;
import com.isssr.foodemperors.model.Category;
import com.isssr.foodemperors.service.CategoryService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by marco on 03/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryEndpoint {

    @Inject
    private CategoryService categoryService;

    @Inject
    private TokenService tokenService;

    @RequestMapping(path = "api/category", method = RequestMethod.POST)
    public Category saveCategory(@RequestBody CategoryDTO categoryDTO, HttpServletRequest request, HttpServletResponse response){
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return categoryService.insertCategory(categoryDTO.getCategory(),categoryDTO.getFather());
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/category/findby/name/{name}", method = RequestMethod.GET)
    public Category searchProduct(@PathVariable String name,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
                return categoryService.findById(name);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/categories/leaf",method = RequestMethod.GET)
    public List<Category> findLeafs(HttpServletRequest request, HttpServletResponse response){
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return categoryService.findLeafs(null);
        else{
            response.setStatus(401);
            return null;
        }

    }

}