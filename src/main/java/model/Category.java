package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Created by marco on 03/06/17.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    @Embedded
    @JsonProperty
    private HashMap<String,String> properties;


    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_father")
    @DBRef
    private Category father;

    public Category(){

    }

    public Category(String id, HashMap<String,String> properties){
        this.id = id;
        this.properties = properties;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public Category getFather() {
        return father;
    }

    public void setFather(Category father) {
        this.father = father;
    }
}
