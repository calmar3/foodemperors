package com.isssr.foodemperors.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;

@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @JsonProperty
    private String id;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_product")
    @DBRef
    private Product product;

    @JsonProperty
    private String expDate;

    @JsonProperty
    private String delDate;


    @JsonProperty
    private Integer remaining;

    @JsonProperty
    private Integer quantity;

    /**
     * STATUS:
     * 0 - Not Delivered
     * 1 - Ready
     * 2 - Delivered
     * 3 - Signaled
     */
    @JsonProperty
    private Integer status;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_order")
    @DBRef
    private Commission commission;

    @JsonProperty
    private Integer number;

    @JsonProperty
    private Double price;


    public Batch () {

    }


    public Batch (Product product, String expDate, String delDate, Integer quantity,
                  Commission commission, Integer status, Integer number, Double price) {
        this.product = product;
        this.expDate = expDate;
        this.delDate = delDate;
        this.quantity = quantity;
        this.commission = commission;
        this.status = status;
        this.number = number;
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice(){
        return this.price;
    }

    public Integer getNumber(){
        return this.number;
    }


    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    @JsonIgnore
    public boolean isDelivered()
    {
        if(status == 2)
            return true;
        else
            return false;
    }

    @JsonIgnore
    public boolean isReady()
    {
        if(status == 1)
            return true;
        else
            return false;
    }
}
