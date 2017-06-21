package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Product;
import com.isssr.foodemperors.repository.ProductRepository;
import com.isssr.foodemperors.service.ProductService;
import org.springframework.web.bind.annotation.*;

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

    @Inject
    private ProductService productService;


    @RequestMapping(path = "api/product", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @RequestMapping(path = "api/product/findby/name/{name}", method = RequestMethod.GET)
    public List<Product> searchProduct(@PathVariable String name) {
        return productRepository.findByName(name);
    }

    @RequestMapping(path = "api/product/findby/category/properties/{strings}", method = RequestMethod.GET)
    public List<Product> searchProductByProperties(@PathVariable String strings) {
        return productService.getByCategoryAndProperties(strings);
    }

    @RequestMapping(path = "api/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }



}
