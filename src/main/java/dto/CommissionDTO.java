package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import model.Batch;
import model.Commission;

import java.util.List;

/**
 * Created by marco on 26/05/17.
 */
public class CommissionDTO {

    @JsonProperty
    private Commission commission;

    @JsonProperty
    private List<Batch> batches;

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }
}
