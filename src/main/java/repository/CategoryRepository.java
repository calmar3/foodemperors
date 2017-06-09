package repository;

import model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 03/06/17.
 */
public interface CategoryRepository  extends MongoRepository<Category, Long> {

    Category findById(String id);

}
