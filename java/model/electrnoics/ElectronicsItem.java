/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.electrnoics;

import model.Item.Item;

/**
 *
 * @author DELL
 */
public class ElectronicsItem extends Item {

    private String color;
    private int Insuarance;

    public ElectronicsItem(String color, int Insuarance, int ID, String Description, Float Price, Float Discount, String SellingStatus, String Image, String Category, String Name) {
        super(ID, Name, Description, Price, Discount, SellingStatus, Image, Category);
        this.color = color;
        this.Insuarance = Insuarance;
    }

    public ElectronicsItem() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getInsuarance() {
        return Insuarance;
    }

    public void setInsuarance(int Insuarance) {
        this.Insuarance = Insuarance;
    }

}
