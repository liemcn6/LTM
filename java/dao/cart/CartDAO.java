/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cart;

import java.util.Date;
import model.order.Cart;

/**
 *
 * @author DELL
 */
public interface CartDAO {
   public Cart getCartByUserID(int ID);
   public int createCartByUserID(int ID);
   public int deleteItemInCartByItemID(int ID);
   public int addItemInCartByItemID(int quantity,int cartID,int itemID);
   public int updateItemAmountByItemID(int quantity,int ID);
}
