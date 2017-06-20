package com.isssr.foodemperors.repository;

import com.isssr.foodemperors.model.Catalogue;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by simone on 09/06/17.
 */
public interface CatalogueRepository  extends MongoRepository<Catalogue, Long> {

    Catalogue findById(String id);
}
