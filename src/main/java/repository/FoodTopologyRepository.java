package repository;

import model.FoodTopology;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 30/05/17.
 */
public interface FoodTopologyRepository extends MongoRepository<FoodTopology, Long> {
}
