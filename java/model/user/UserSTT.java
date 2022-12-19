/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author DELL
 */
public class UserSTT {
    private String phone;
    private String mail;
    private String numberHouse;
    private String street;
    private String distinct;
    private String city;
    private String firstname;
    private String midname;
    private String lastname;

    public UserSTT() {
    }

    public UserSTT(String phone, String mail, String numberHouse, String street, String distinct, String city, String firstname, String midname, String lastname) {
        this.phone = phone;
        this.mail = mail;
        this.numberHouse = numberHouse;
        this.street = street;
        this.distinct = distinct;
        this.city = city;
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
}
