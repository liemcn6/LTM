/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author Admin
 */
public class Address {

    private int id;
    private String addressDetail;
    private String district;
    private String city;

    public Address() {
    }

    public Address(int id, String houseNumber, String street, String district, String city) {
        this.id = id;
        this.addressDetail = addressDetail;
        this.district = district;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

}
