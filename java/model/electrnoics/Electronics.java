/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.electrnoics;

/**
 *
 * @author DELL
 */
public class Electronics {
    private int id;
    private String name;
    private String type;
    private String version;
    private float cost;
    private int RemainingQuantity;
    private String size;
    private String weight;
    public Electronics() {
    }

    public Electronics(int id, String name, String type, String version, float cost, int RemainingQuantity, String size, String weight) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.version = version;
        this.cost = cost;
        this.RemainingQuantity = RemainingQuantity;
        this.size = size;
        this.weight = weight;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getRemainingQuantity() {
        return RemainingQuantity;
    }

    public void setRemainingQuantity(int RemainingQuantity) {
        this.RemainingQuantity = RemainingQuantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

   
}
