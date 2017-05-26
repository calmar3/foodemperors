package repository;

import model.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 26/05/17.
 */
public interface BatchRepository extends MongoRepository<Batch, Long> {


}