package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import java.util.List;

/**
 * Created by mariusdragosionita on 23/05/17.
 */
@Embeddable
public class Property {

    @JsonProperty
    private String description;

    @JsonProperty
    private List<String> values;

    public Property (){

    }

    public Property (String description, List<String> values) {

        this.description = description;
        this.values = values;

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
