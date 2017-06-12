package repository;

import model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by mariusdragosionita on 23/05/17.
 */
public interface ProductRepository extends MongoRepository<Product, Long> {

    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByPropertiesIn (String properties);
    List<Product> findByStockist (String stockist);
    Product findById (String id);

}
