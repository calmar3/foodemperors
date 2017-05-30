package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;

/**
 * Created by marco on 30/05/17.
 */
@Entity
public class FoodTopology extends Topology{



    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_food")
    @DBRef
    private Food food;



    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }


}
