/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.electrnoics;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class ElectronicOrigin {
    private int id;
    private String companyName;
    private String address;
    private Date dateOfManufacture;
    private String brand;

    public ElectronicOrigin() {
    }

    public ElectronicOrigin(int id,String companyName, String address, Date dateOfManufacture, String brand) {
        this.id=id;
        this.companyName = companyName;
        this.address = address;
        this.dateOfManufacture = dateOfManufacture;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
