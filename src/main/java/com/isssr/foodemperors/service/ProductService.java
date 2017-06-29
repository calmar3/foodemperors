package com.isssr.foodemperors.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isssr.foodemperors.model.Product;
import org.springframework.stereotype.Service;
import com.isssr.foodemperors.repository.ProductRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by marco on 12/06/17.
 */
@Service
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        product.setDescription("");
        for (String key : product.getProperties().keySet()) {
            String desc = product.getDescription();
            desc += product.getProperties().get(key) + " ";
            product.setDescription(desc);
        }
        return productRepository.save(product);
    }

    public List<Product> getByCategoryAndProperties(String properties){
        String[] props = properties.split(" - ");
        String[] productProps = props[1].split(" ");
        List<Product> products = productRepository.findByCategoryId(props[0]);
        for(Product product:products){
            for (int i = 0 ; i < productProps.length ; i++ ){
                if (!(product.getDescription().toLowerCase().contains(productProps[i].toLowerCase()))){
                    products.remove(product);
                    break;
                }
            }
        }
        return products;
    }

}
