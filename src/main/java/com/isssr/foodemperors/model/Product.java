package com.isssr.foodemperors.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mariusdragosionita on 19/05/17.
 */

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String averageDeliveryTime;     //relativo a consegne dal magazzino centrale verso magazzini locali

    @JsonProperty
    private String stockist;

    @JsonProperty
    private String price;

    @JsonProperty
    @Embedded
    private HashMap<String,String> properties;


    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_category")
    @DBRef
    private Category category;


    @JsonProperty
    private String description;

    @ManyToMany
    @JoinColumn(name = "id_properties")
    @DBRef
    @JsonProperty
    @JsonIgnore
    private List<Property> propertyList;

    public Product() {

    }

    public Product (String name, String averageDeliveryTime, String stockist, String price,
                    HashMap<String, String> properties,Category category,String description,
                    List<Property> propertyList) {
        this.name = name;
        this.averageDeliveryTime = averageDeliveryTime;
        this.stockist = stockist;
        this.price = price;
        this.properties = properties;
        this.category = category;

        this.description = description;
        this.propertyList = propertyList;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(String averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public String getStockist() {
        return stockist;
    }

    public void setStockist(String stockist) {
        this.stockist = stockist;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory(){
        return this.category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }


    @JsonGetter
    public List<String> getPropertiesId() {
        List<String> idList = new ArrayList<>();
        List<Property> propertyList = getPropertyList();

        if(propertyList == null)
            return null;


        for(Property property : propertyList) {
            if(property != null) {
                idList.add(property.getId());
            }
        }
        if(idList.size() > 0) {
            return idList;
        }
        else {
            return null;
        }
    }
}
