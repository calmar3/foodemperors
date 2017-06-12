package com.isssr.foodemperors.service.spec;

import com.isssr.foodemperors.model.Property;

import java.util.List;

/**
 * Created by Caim03 on 12/06/17.
 */
public interface PropertyService {
    public Property saveProperty(Property property);
    public List<Property> searchAll();
    public Property searchByName(String name);
}
