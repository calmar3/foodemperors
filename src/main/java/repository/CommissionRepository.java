package repository;

import model.Commission;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by marco on 26/05/17.
 */
public interface CommissionRepository extends MongoRepository<Commission, String> {

    Commission findByNumber(String Number);
}