/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.shoes;

/**
 *
 * @author DELL
 */
public class Shoes {

    private int id;
    private String name;
    private int RemainingQuantity;
    private float cost;
    private String version;
    private ShoesDesign sd;
    private ShoesOrigin so;

    public Shoes() {
    }

    public Shoes(int id, String name, int RemainingQuantity, float cost, String version) {
        this.id = id;
        this.name = name;
        this.RemainingQuantity = RemainingQuantity;
        this.cost = cost;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemainingQuantity() {
        return RemainingQuantity;
    }

    public void setRemainingQuantity(int RemainingQuantity) {
        this.RemainingQuantity = RemainingQuantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ShoesDesign getSd() {
        return sd;
    }

    public void setSd(ShoesDesign sd) {
        this.sd = sd;
    }

    public ShoesOrigin getSo() {
        return so;
    }

    public void setSo(ShoesOrigin so) {
        this.so = so;
    }

}
