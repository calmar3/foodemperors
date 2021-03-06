package com.isssr.foodemperors.service;

import com.isssr.foodemperors.model.Product;
import com.isssr.foodemperors.model.Property;
import com.isssr.foodemperors.repository.ProductRepository;
import com.isssr.foodemperors.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
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
        for(int j = 0 ; j < products.size() ; j++){
            for (int i = 0 ; i < productProps.length ; i++ ){
                if (! (products.get(j).getDescription().toLowerCase().contains(productProps[i].toLowerCase())) &&
                        !(products.get(j).getName().toLowerCase().contains(productProps[i].toLowerCase())) &&
                        !(products.get(j).getStockist().toLowerCase().contains(productProps[i].toLowerCase()))){
                    products.remove(products.get(j));
                    j--;
                    break;
                }
            }
        }
        return products;
    }

    public List<Product> findProductByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Long deleteProductById(String id){
        return productRepository.deleteById(id);
    }

}
