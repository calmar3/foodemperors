package com.isssr.foodemperors.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Caim03 on 23/06/17.
 */

@Entity
public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    @JsonProperty
    private String date;

    @JsonProperty
    /* Ditta che cede il bene o i beni */
    private String business;

    @ManyToMany
    @JoinColumn(name = "id_batch")
    @DBRef
    @JsonProperty
    private List<Batch> batches;

    public DeliveryNote() {}

    public DeliveryNote(String date, String business, List<Batch> batches) {
        this.date = date;
        this.business = business;
        this.batches = batches;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
