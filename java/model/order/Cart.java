/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

/**
 *
 * @author Admin
 */
public class Cart {
    private int id;
    private float totalPrice;

   public Cart(){
       
   }
    public Cart(int id,int Quantity, float totalPrice) {
        this.id=id;
        this.totalPrice = totalPrice;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }


    public double getTotalPrice() {
        return totalPrice;
    }
    
    
}
