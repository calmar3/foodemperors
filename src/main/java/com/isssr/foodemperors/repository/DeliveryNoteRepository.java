package com.isssr.foodemperors.repository;

import com.isssr.foodemperors.model.DeliveryNote;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Caim03 on 23/06/17.
 */
public interface DeliveryNoteRepository extends MongoRepository<DeliveryNote, Long> {
    DeliveryNote findById(String id);
}
