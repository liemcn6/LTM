/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.book;

import model.Item.Item;

/**
 *
 * @author DELL
 */
public class BookItem extends Item{
    public BookItem() {
    }

    public BookItem(int ID, String Name, String Description, Float Price, Float Discount, String SellingStatus, String Image, String Category) {
        super(ID, Name, Description, Price, Discount, SellingStatus, Image, Category);
    }
    
}