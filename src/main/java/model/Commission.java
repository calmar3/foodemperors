package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.aspectj.weaver.ast.Or;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by mariusdragosionita on 23/05/17.
 */
@Entity
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    @JsonProperty
    private String number;

    @JsonProperty
    private String date;

    @JsonProperty
    private String source;

    @JsonProperty
    private String destination;

    @JsonProperty
    private String deliveryTime;

    public Commission(){

    }

    public Commission(String number, String date, String source, String destination, String deliveryTime) {

        this.number = number;
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.deliveryTime = deliveryTime;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
