/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.order;

import dao.utils.ConDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.Cart;
import model.user.User;
import model.user.UserSTT;

/**
 *
 * @author DELL
 */
public class OrderDAOImpl implements OrderDAO {

    PreparedStatement preStatement;
    PreparedStatement preStatement1;
    PreparedStatement preStatement2;
    ResultSet rs;
    Statement Statement;
    Statement Statement1;
    private Connection conn;

    public OrderDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int createOrder(int userID, String type, float cost, Date createdDate, String status, float amount) {
        String sql1 = "INSERT INTO shopbanhang.shipment (Type, Cost) VALUES (?, ?);";
        String sql2 = "INSERT INTO shopbanhang.order (ShipmentID, UserID, CreatedDate) VALUES (?, ?, ?);";
        String sql3 = "INSERT INTO shopbanhang.payment (OrderID, Status, Amount) VALUES (?, ?, ?);";
        try {
            preStatement = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            preStatement.setString(1, type);
            preStatement.setFloat(2, cost);
            int rowcount = preStatement.executeUpdate();
            rs = preStatement.getGeneratedKeys();
            int shipmentid = 0;
            if (rs.next()) {
                shipmentid = rs.getInt(1);
            }
            preStatement1 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            preStatement1.setInt(1, shipmentid);
            preStatement1.setInt(2, userID);
            preStatement1.setDate(3, new java.sql.Date(createdDate.getTime()));
            int rowcount1 = preStatement1.executeUpdate();
            rs = preStatement1.getGeneratedKeys();
            int orderid = 0;
            if (rs.next()) {
                orderid = rs.getInt(1);
            }
            preStatement2 = conn.prepareStatement(sql3);
            preStatement2.setInt(1, orderid);
            preStatement2.setString(2, status);
            preStatement2.setFloat(3, amount);
            int rowcount2 = preStatement2.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateOrder(int orderID, String type, float cost, Date createdDate, String status, float amount) {
        String sql1 = "Update shopbanhang.order\n"
                + "set createdDate= ? \n"
                + "where id= " + orderID + ";";
        String sql2 = "Update payment\n"
                + "set Status=?,Amount=?\n"
                + "where OrderID=" + orderID + ";";
        String sql3 = "Select ShipmentID from shopbanhang.order Where ID=" + orderID + ";";
        String sql4 = "Update shipment\n"
                + "set Type=?,Cost=?\n"
                + "where id=?";
        try {
            preStatement = conn.prepareStatement(sql1);
            preStatement.setDate(1, new java.sql.Date(createdDate.getTime()));
            int rowcout = preStatement.executeUpdate();
            preStatement1 = conn.prepareStatement(sql2);
            preStatement1.setString(1, status);
            preStatement1.setFloat(2, amount);
            int rowcout1 = preStatement1.executeUpdate();
            Statement = conn.createStatement();
            rs = Statement.executeQuery(sql3);
            int shipmentid = 0;
            while (rs.next()) {
                shipmentid = rs.getInt(1);
            }
            preStatement2 = conn.prepareStatement(sql4);
            preStatement2.setString(1, type);
            preStatement2.setFloat(2, cost);
            preStatement2.setInt(3, shipmentid);
            int rowcout2 = preStatement2.executeUpdate();
            return rowcout2;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int deleteOrder(int orderID) {
        String sql1 = "Select ShipmentID from shopbanhang.order Where ID=" + orderID + ";";
        String sql2 = "Delete  from shipment where ID= ? ;";
        String sql3 = "Delete from payment where OrderID= ? ;";
        String sql4 = "Delete from shopbanhang.order where ID=? ;";
        try {
            Statement = conn.createStatement();
            rs = Statement.executeQuery(sql1);
            int shipmentid = 0;
            while (rs.next()) {
                shipmentid = rs.getInt(1);
            }
            preStatement = conn.prepareStatement(sql2);
            preStatement.setInt(1, shipmentid);
            int rowcount = preStatement.executeUpdate();
            preStatement1 = conn.prepareStatement(sql3);
            preStatement1.setInt(1, orderID);
            int rowcount1 = preStatement1.executeUpdate();
            preStatement2 = conn.prepareStatement(sql4);
            preStatement2.setInt(1, orderID);
            int rowcount2 = preStatement2.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public UserSTT getUser(int userID) {
        String sql1 = "Select user.Phone,user.mail,address.NumberHouse,address.Street,address.Distincts,address.City,fullname.FirstName,fullname.Midname,fullname.LastName \n"
                + "from((user\n"
                + "inner join address on user.ID=address.UserID)\n"
                + "inner join fullname on user.ID=fullname.UserID) \n"
                + "Where user.id=" + userID + ";";
        try {
            Statement = conn.createStatement();
            rs = Statement.executeQuery(sql1);
            UserSTT u = new UserSTT();
            while (rs.next()) {
                u.setPhone(rs.getString(1));
                u.setMail(rs.getString(2));
                u.setNumberHouse(rs.getString(3));
                u.setStreet(rs.getString(4));
                u.setDistinct(rs.getString(5));
                u.setCity(rs.getString(6));
                u.setFirstname(rs.getString(7));
                u.setMidname(rs.getString(8));
                u.setLastname(rs.getString(9));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public ArrayList<Cart> getCart(int orderID) {
        String sql = "SELECT * FROM shopbanhang.cart WHERE OrderID=" + orderID + ";";
        try {
            Statement = conn.createStatement();
            rs = Statement.executeQuery(sql);
            ArrayList<Cart> list = new ArrayList<>();
            while (rs.next()) {
                Cart c = new Cart();
                c.setTotalPrice(rs.getFloat(4));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
