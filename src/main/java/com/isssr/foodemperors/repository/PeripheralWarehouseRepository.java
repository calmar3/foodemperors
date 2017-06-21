package com.isssr.foodemperors.repository;

import com.isssr.foodemperors.model.PeripheralWarehouse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * Created by olga 29/05/17.
 */


public interface PeripheralWarehouseRepository extends MongoRepository<PeripheralWarehouse, Long> {

    List<PeripheralWarehouse> findAll();
    PeripheralWarehouse findById(String id);
    Long deleteById(String Id);
}

