package repository;

import model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by marco on 03/06/17.
 */
public interface CategoryRepository  extends MongoRepository<Category, Long> {

    List<Category> findAll();
    List<Category> findByFather(String father);
    Category findById(String id);
}
