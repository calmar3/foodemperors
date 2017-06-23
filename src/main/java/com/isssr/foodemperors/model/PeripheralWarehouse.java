package com.isssr.foodemperors.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by olga on 29/05/17.
 */
@Entity
public class PeripheralWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String address;

    @JsonProperty
    private String phone;

    @JsonProperty
    private String serverAddress;

    @JsonProperty
    private String warehouseManager;

    @JsonProperty
    private Integer employeesNumbers;

    @JsonProperty
    private String pIVA;

    public PeripheralWarehouse() {

    }

    public PeripheralWarehouse(String name,String address,String phone, String serverAddress,String warehouseManager,Integer employeesNumbers, String pIVA){
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.serverAddress = serverAddress;
        this.warehouseManager = warehouseManager;
        this.employeesNumbers = employeesNumbers;
        this.pIVA = pIVA;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getWarehouseManager() {
        return warehouseManager;
    }

    public void setWarehouseManager(String warehouseManager) {
        this.warehouseManager = warehouseManager;
    }

    public Integer getEmployeesNumbers() {
        return employeesNumbers;
    }

    public void setEmployeesNumbers(Integer employeesNumbers) {
        this.employeesNumbers = employeesNumbers;
    }

    public String getPIVA() {
        return pIVA;
    }

    public void setPIVA(String PIVA) {
        this.pIVA = pIVA;
    }
}
