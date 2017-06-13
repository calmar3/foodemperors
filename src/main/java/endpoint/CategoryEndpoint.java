package endpoint;


import dto.CategoryDTO;
import model.Category;
import org.springframework.web.bind.annotation.*;
import repository.CategoryRepository;
import service.CategoryService;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by marco on 03/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryEndpoint {

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CategoryService categoryService;

    @RequestMapping(path = "api/category", method = RequestMethod.POST)

    public Category saveCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.insertCategory(categoryDTO.getCategory(),categoryDTO.getFather());
    }

    @RequestMapping(path = "api/category/findby/name/{name}", method = RequestMethod.GET)
    public Category searchProduct(@PathVariable String name) {
        return categoryRepository.findById(name);

    }

    @RequestMapping(path = "api/categories/leaf",method = RequestMethod.GET)
    public List<Category> findLeafs(){
        return categoryRepository.findBySons(null);
    }




}