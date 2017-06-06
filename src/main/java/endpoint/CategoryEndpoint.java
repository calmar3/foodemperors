package endpoint;

import model.Category;
import org.springframework.web.bind.annotation.*;
import repository.CategoryRepository;

import javax.inject.Inject;

/**
 * Created by marco on 03/06/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryEndpoint {

    @Inject
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "api/category", method = RequestMethod.POST)
    public Category saveCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
    

}