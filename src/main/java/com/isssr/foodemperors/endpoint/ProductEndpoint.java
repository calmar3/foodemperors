package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Product;
import org.springframework.web.bind.annotation.*;
import com.isssr.foodemperors.repository.ProductRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariusdragosionita on 23/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class ProductEndpoint {

    @Inject
    private ProductRepository productRepository;

    /**
     * Non testato
     * @param product
     * @return
     */
    @RequestMapping(path = "api/product", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product) {

        product.setDescription("");
        for (String key : product.getProperties().keySet()) {
            String desc = product.getDescription();
            desc += product.getProperties().get(key) + " ";
            product.setDescription(desc);
        }
        return productRepository.save(product);
    }

    @RequestMapping(path = "api/product/findby/name/{name}", method = RequestMethod.GET)
    public List<Product> searchProduct(@PathVariable String name) {
        return productRepository.findByName(name);
     }

    @RequestMapping(path = "api/product/findby/properties/{properties}", method = RequestMethod.GET)
    public List<Product> searchProductByProperties(@PathVariable String properties) {
        String[] props = properties.split(" ");
        List<Product> products = new ArrayList<>();
        products.addAll(productRepository.findByDescriptionLike(props[0]));
        for(Product product:products){
            for (int i = 1 ; i < props.length ; i++ ){
                if (!(product.getDescription().toLowerCase().contains(props[i].toLowerCase()))){
                    products.remove(product);
                    break;
                }
            }
        }
        return products;
    }

    @RequestMapping(path = "api/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
