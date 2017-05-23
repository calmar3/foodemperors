package endpoint;

import model.Product;
import model.User;
import org.springframework.web.bind.annotation.*;
import repository.ProductRepository;

import javax.inject.Inject;

/**
 * Created by mariusdragosionita on 23/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class ProductEndpoint {

    @Inject
    private ProductRepository productRepository;

    @RequestMapping(path = "api/product", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }




}
