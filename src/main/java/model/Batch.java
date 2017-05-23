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
    private String productId;

    @JsonProperty
    private String expDate;

    @JsonProperty
    private String delDate;

    @JsonProperty
    private Integer quantity;

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_order")
    @DBRef
    private String orderId;

    public Batch () {

    }

    public Batch (String productId, String expDate, String delDate, Integer quantity, String orderId) {
        this.productId = productId;
        this.expDate = expDate;
        this.delDate = delDate;
        this.quantity = quantity;
        this.orderId = orderId;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
