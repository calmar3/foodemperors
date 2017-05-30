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
public class Topology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
}
