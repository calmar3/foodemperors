package model;

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
    private Integer quantity;


    @JsonProperty
    private String delivered;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_order")
    @DBRef
    private Commission commission;

    public Batch () {

    }

    public Batch (Product product, String expDate, String delDate, Integer quantity, Commission commission, String delivered) {
        this.product = product;
        this.expDate = expDate;
        this.delDate = delDate;
        this.quantity = quantity;
        this.commission = commission;
        this.delivered = delivered;

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

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

}
