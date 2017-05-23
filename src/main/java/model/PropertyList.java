package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by marco on 23/05/17.
 */
@Entity
public class PropertyList {

    @Id
    @JsonProperty
    public static final String id = "product_properties";

    @Embedded
    @JsonProperty
    public List<Property> properties;

    public PropertyList(){

    }

}
