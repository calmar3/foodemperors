package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Locale;

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
    private HashMap<String,String> properties;


    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_topology")
    @DBRef
    private Category category;

    public Product() {

    }

    public Product (String name, String averageDeliveryTime, String stockist, String price,
                    HashMap<String, String> properties, Topology topology, Boolean outdated) {

        this.name = name;
        this.averageDeliveryTime = averageDeliveryTime;
        this.stockist = stockist;
        this.price = price;
        this.properties = properties;
        this.topology = topology;
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


    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public Topology getTopology() {
        return topology;
    }

    public void setTopology(Topology topology) {
        this.topology = topology;
    }

    public void setOutdated(boolean outdated) {
        this.outdated = outdated;
    }
}
