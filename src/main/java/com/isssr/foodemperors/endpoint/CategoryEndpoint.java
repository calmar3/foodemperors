package com.isssr.foodemperors.endpoint;


import com.isssr.foodemperors.dto.CategoryDTO;
import com.isssr.foodemperors.model.Category;
import com.isssr.foodemperors.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by marco on 03/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryEndpoint {

    @Inject
    private CategoryService categoryService;

    @RequestMapping(path = "api/category", method = RequestMethod.POST)
    public Category saveCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.insertCategory(categoryDTO.getCategory(),categoryDTO.getFather());
    }

    @RequestMapping(path = "api/category/findby/name/{name}", method = RequestMethod.GET)
    public Category searchProduct(@PathVariable String name) {
        return categoryService.findById(name);

    }

    @RequestMapping(path = "api/categories/leaf",method = RequestMethod.GET)
    public List<Category> findLeafs(){
        return categoryService.findLeafs(null);
    }

}