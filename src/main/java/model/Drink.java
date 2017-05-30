package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by marco on 30/05/17.
 */
@Entity
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    public Drink(){}

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
}
