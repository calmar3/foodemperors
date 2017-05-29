package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
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
    private Boolean outdated;

    @JsonProperty
    @Embedded
    private List<String> properties;

    public Product() {

    }

    public Product (String name, String averageDeliveryTime, String stockist, String price, List<String> properties, Boolean outdated) {

        this.name = name;
        this.averageDeliveryTime = averageDeliveryTime;
        this.stockist = stockist;
        this.price = price;
        this.properties = properties;
        this.outdated = outdated;

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


    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public Boolean getOutdated() {
        return outdated;
    }

    public void setOutdated(Boolean outdated) {
        this.outdated = outdated;
    }
}
