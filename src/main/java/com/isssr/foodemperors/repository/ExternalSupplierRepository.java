package com.isssr.foodemperors.repository;

import com.isssr.foodemperors.model.ExternalSupplier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by marco on 26/06/17.
 */
public interface ExternalSupplierRepository extends MongoRepository<ExternalSupplier, Long> {

    List<ExternalSupplier> findAll();
    Long deleteById(String id);
}
