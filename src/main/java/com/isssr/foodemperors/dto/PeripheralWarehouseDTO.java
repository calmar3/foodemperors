package com.isssr.foodemperors.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isssr.foodemperors.model.PeripheralWarehouse;

/**
 * Created by olga on 27/05/17.
 */
public class PeripheralWarehouseDTO {

    @JsonProperty
    private PeripheralWarehouse peripheralWarehouse;

    
    public PeripheralWarehouse getPeripheralWarehouse() {
        return peripheralWarehouse;
    }

    public void setPeripheralWarehouse(PeripheralWarehouse peripheralWarehouse) {
        this.peripheralWarehouse = peripheralWarehouse;
    }
}