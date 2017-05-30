package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;

/**
 * Created by marco on 30/05/17.
 */
public class DrinkTopology extends Topology{

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_drink")
    @DBRef
    private Drink drink;

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Drink getDrink(){
        return this.drink;
    }
}
