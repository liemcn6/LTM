/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.clothes;

/**
 *
 * @author DELL
 */
public class Clothes {
    private int id;
    private String name;
    private int RemainingQuantity;
    private float cost;
    private String version;
    private ClothesDesign cd;
    private ClothesOrigin co;

    public Clothes() {
    }

    public Clothes(int id, String name, int RemainingQuantity, float cost, String version) {
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

    public int getRemainingquantity() {
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

    public ClothesDesign getCd() {
        return cd;
    }

    public void setCd(ClothesDesign cd) {
        this.cd = cd;
    }

    public ClothesOrigin getCo() {
        return co;
    }

    public void setCo(ClothesOrigin co) {
        this.co = co;
    }

}
