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
    public String id;

    @Embedded
    @JsonProperty
    public List<Property> properties;

    public PropertyList(){

    }

    public PropertyList(List<Property> properties) {

        this.properties = properties;

    }

}
