package com.isssr.foodemperors.endpoint;

import com.isssr.foodemperors.model.Product;
import com.isssr.foodemperors.service.ProductService;
import com.isssr.foodemperors.service.TokenService;
import com.isssr.foodemperors.utils.TokenPayload;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by mariusdragosionita on 23/05/17.
 */
@RestController
@CrossOrigin(origins = "*")
public class ProductEndpoint {

    @Inject
    private ProductService productService;

    @Inject
    private TokenService tokenService;


    //TODO MERGE WITH API/PRODUCT/SAVE
    @RequestMapping(path = "api/product", method = RequestMethod.POST)
    public Product saveProduct(@RequestBody Product product,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return productService.saveProduct(product);
        else {
            response.setStatus(401);
            return null;
        }
    }


    @RequestMapping(path = "api/product/findby/name/{name}", method = RequestMethod.GET)
    public List<Product> searchProduct(@PathVariable String name,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return productService.findProductByName(name);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/product/findby/category/properties/{strings}", method = RequestMethod.GET)
    public List<Product> searchProductByProperties(@PathVariable String strings,HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager") || tokenPayload.getRole().equals("wharehouseman")))
            return productService.getByCategoryAndProperties(strings);
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/products", method = RequestMethod.GET)
    public List<Product> getAllProducts(HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager")))
            return productService.findAllProducts();
        else{
            response.setStatus(401);
            return null;
        }
    }

    @RequestMapping(path = "api/product/{id}", method = RequestMethod.DELETE)
    public Long deleteProduct(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
        if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                || tokenPayload.getRole().equals("manager"))){
            Long product =  productService.deleteProductById(id);
            if (product == 0){
                response.setStatus(404);
                return null;
            }
            return product;
        }
        else {
            response.setStatus(401);
            return null;
        }

    }

    @RequestMapping(path = "api/product", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product, HttpServletRequest request, HttpServletResponse response){
            TokenPayload tokenPayload = tokenService.validateUser(request.getHeader("token"));
            if (tokenPayload != null && (tokenPayload.getRole().equals("admin")
                    || tokenPayload.getRole().equals("manager")))
                return productService.saveProduct(product);
            else {
                response.setStatus(401);
                return null;
            }
        }



}
