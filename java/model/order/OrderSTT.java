/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class OrderSTT {
    private String type;
    private float cost;
    private Date createdDate;
    private String status;
    private float ammount;

    public OrderSTT() {
    }

    public OrderSTT(String type, float cost, Date createdDate, String status, float ammount) {
        this.type = type;
        this.cost = cost;
        this.createdDate = createdDate;
        this.status = status;
        this.ammount = ammount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAmmount() {
        return ammount;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }
}
