/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Item;

/**
 *
 * @author ADMIN
 */
public class Item {

    private int ID;
    private String Name;
    private String Description;
    private Float Price;
    private Float Discount;
    private String SellingStatus;
    private String Image;
    
    //1 - book 
    //2 - clothes 
    //3 - shoes 
    //4 - elec
    private String Category;

    public Item(int ID, String Name, String Description, Float Price, Float Discount, String SellingStatus, String Image, String Category) {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Discount = Discount;
        this.SellingStatus = SellingStatus;
        this.Image = Image;
        this.Category = Category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Item() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }

    public Float getDiscount() {
        return Discount;
    }

    public void setDiscount(Float Discount) {
        this.Discount = Discount;
    }

    public String getSellingStatus() {
        return SellingStatus;
    }

    public void setSellingStatus(String SellingStatus) {
        this.SellingStatus = SellingStatus;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

}
