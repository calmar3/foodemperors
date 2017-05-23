package model;

import java.util.List;

/**
 * Created by mariusdragosionita on 23/05/17.
 */

public class Property {

    private String description;
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
