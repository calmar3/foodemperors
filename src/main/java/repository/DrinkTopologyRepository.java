package repository;

import model.DrinkTopology;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 30/05/17.
 */
public interface DrinkTopologyRepository extends MongoRepository<DrinkTopology, Long> {
}
