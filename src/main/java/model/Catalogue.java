package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.*;

/**
 * Created by simone on 09/06/17.
 */
@Entity
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @JsonProperty
    @ManyToOne
    @JoinColumn(name = "id_product")
    @DBRef
    private Product product;


    @JsonProperty
    private Integer quantity;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
