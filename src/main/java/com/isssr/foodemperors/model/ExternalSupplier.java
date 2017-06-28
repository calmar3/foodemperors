package com.isssr.foodemperors.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by marco on 26/06/17.
 */
@Entity
public class ExternalSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String vat;

    @JsonProperty
    private String partnership;

    @JsonProperty
    private String address;

    @JsonProperty
    private String mail;

    public ExternalSupplier(){
    }

    public String getPartnership() {
        return partnership;
    }

    public void setPartnership(String partnership) {
        this.partnership = partnership;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
