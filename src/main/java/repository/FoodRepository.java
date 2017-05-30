package repository;

import model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 30/05/17.
 */
public interface FoodRepository extends MongoRepository<Food, Long> {
}
