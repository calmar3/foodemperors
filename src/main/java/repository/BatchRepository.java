package repository;

import model.Batch;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by marco on 26/05/17.
 */
public interface BatchRepository extends MongoRepository<Batch, Long> {

    List<Batch> findByCommissionId(String Id);
}