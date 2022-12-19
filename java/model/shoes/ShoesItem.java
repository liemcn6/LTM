/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.shoes;

import model.Item.Item;

/**
 *
 * @author DELL
 */
public class ShoesItem extends Item {

    private String color;
    private String size;

    public ShoesItem(String color, String size, int ID, String Description, Float Price, Float Discount, String SellingStatus, String Image, String Category, String Name) {
        super(ID, Name, Description, Price, Discount, SellingStatus, Image, Category);
        this.color = color;
        this.size = size;
    }

    public ShoesItem() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
