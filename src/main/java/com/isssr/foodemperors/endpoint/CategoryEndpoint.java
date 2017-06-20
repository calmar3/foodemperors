package com.isssr.foodemperors.endpoint;


import com.isssr.foodemperors.dto.CategoryDTO;

import com.isssr.foodemperors.model.Category;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.CategoryRepository;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by marco on 03/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryEndpoint {

    @Inject
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "api/category", method = RequestMethod.POST)

    public Category saveCategory(@RequestBody CategoryDTO categoryDTO) {

        Category category = categoryDTO.getCategory();

        if (categoryDTO.getFather() == null) {
            return categoryRepository.save(category);
        }
        Category father = categoryRepository.findById(categoryDTO.getFather());
        List<Category> sons = father.getSons();
        if (sons == null)
            sons = new ArrayList<>();
        sons.add(category);
        father.setSons(sons);
        categoryRepository.save(father);
        category.setFather(father);
        return categoryRepository.save(category);
    }

   /* @RequestMapping(path = "api/category/findby/name/{name}", method = RequestMethod.GET)
    public Category searchProduct(@PathVariable String name) {
        return categoryRepository.findById(name);

    }*/

    @RequestMapping(path = "api/category", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryRepository.findAll();

    }


    @RequestMapping(path = "api/category/findby/id/{id}", method = RequestMethod.GET)
    public Category takeCategory(@PathVariable String name) {
        return categoryRepository.findById(name);


    }
    @RequestMapping(path = "api/category/findby/name/{name}", method = RequestMethod.GET)
    public Category searchProduct (@PathVariable String name){
        return categoryRepository.findById(name);

    }
}