/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public interface OrderDAO <T> {
    int createOrder(int userID,String type,float cost,Date createdDate,String status,float amount);
    int updateOrder(int orderID,String type,float cost,Date createdDate,String status,float amount);
    int deleteOrder(int orderID);
    T getUser(int userID);
    ArrayList<T> getCart(int cartID);
}
