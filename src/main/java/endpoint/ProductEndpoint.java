package endpoint;

import model.Product;
import org.springframework.web.bind.annotation.*;
import repository.ProductRepository;

import javax.inject.Inject;
import java.util.List;

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


    @RequestMapping(path = "api/product/findby/name/{name}", method = RequestMethod.GET)
    public List<Product> searchProduct(@PathVariable String name) {
        return productRepository.findByName(name);

     }

    @RequestMapping(path = "api/product", method = RequestMethod.GET)
    public List<Product> takeProduct() {
        return productRepository.findAll();

    }

    @RequestMapping(path = "api/product/findby/properties/{properties}", method = RequestMethod.GET)
    public List<Product> searchProductByProperties(@PathVariable String properties) {
        return productRepository.findByPropertiesIn(properties);

    }

    @RequestMapping(path = "api/product", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }


    @RequestMapping(path = "api/product/{id}", method = RequestMethod.DELETE)
    public Product eliminateProduct(@PathVariable String id) {
        Product p = productRepository.findById(id);
        p.setOutdated(true);
        return productRepository.save(p);

    }


}
