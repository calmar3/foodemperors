package repository;

import model.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 30/05/17.
 */
public interface DrinkRepository extends MongoRepository<Drink, Long> {
}
