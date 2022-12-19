/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.OrderSTT;
import model.user.Account;
import model.user.Address;
import model.user.FullName;
import model.user.User;
import utils.HashGenerator;

/**
 *
 * @author Admin
 */
public class UserDAOImpl implements UserDAO<OrderSTT> {

    private Connection conn;

    private final String CHECK_USERNAME_EXIST_SQL = "SELECT 1 FROM account WHERE username = ?";

    private final String CREATE_EMPTY_USER_SQL = "INSERT INTO user VALUES ()";
    private final String CREATE_EMPTY_USER_FULLNAME_SQL = "INSERT INTO fullname (userid) VALUES (?)";
    private final String CREATE_EMPTY_USER_ADDRESS_SQL = "INSERT INTO address (userid) VALUES (?)";
    private final String CREATE_USER_ACCOUNT_SQL = "INSERT INTO account (userid, username, password) values (?, ?, ?)";

    private final String GET_USER_BY_USERNAME_SQL = "SELECT * FROM user, account, fullname, address "
            + "WHERE account.username = ? AND user.id = account.userid AND user.id = fullname.userid AND user.id = address.userid";
    private final String GET_USER_BY_USERID_SQL = "SELECT * FROM user, account, fullname, address "
            + "WHERE user.id = ? AND user.id = account.userid AND user.id = fullname.userid AND user.id = address.userid";

    public UserDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public boolean checkUsernameExist(String username) {
        try (PreparedStatement ps = conn.prepareStatement(CHECK_USERNAME_EXIST_SQL)) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public User createUserAccount(Account account) {
        User user = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.prepareStatement(CREATE_EMPTY_USER_SQL, Statement.RETURN_GENERATED_KEYS);

            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {
                rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int userId = rs.getInt(1);

                    ps = conn.prepareStatement(CREATE_EMPTY_USER_FULLNAME_SQL);
                    ps.setInt(1, userId);
                    ps.executeUpdate();

                    ps = conn.prepareStatement(CREATE_EMPTY_USER_ADDRESS_SQL);
                    ps.setInt(1, userId);
                    ps.executeUpdate();

                    ps = conn.prepareStatement(CREATE_USER_ACCOUNT_SQL);
                    ps.setInt(1, userId);
                    ps.setString(2, account.getUsername());
                    ps.setString(3, account.getPassword());

                    rowAffected = ps.executeUpdate();

                    if (rowAffected > 0) {
                        user = new User();
                        user.setId(userId);
                        user.setAccount(account);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }

    }

    @Override
    public OrderSTT viewOrder(int orderID) {
        String sql1 = "SELECT * FROM shopbanhang.order WHERE ID=" + orderID + ";";
        OrderSTT o = new OrderSTT();
        Statement statement;
        ResultSet rs;

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql1);
            int shipmentid = 0;

            while (rs.next()) {
                shipmentid = rs.getInt("ShipmentID");
                o.setCreatedDate(rs.getDate("CreatedDate"));
            }

            String sql2 = "SELECT * FROM shopbanhang.shipment WHERE ID=" + shipmentid + ";";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql2);

            while (rs.next()) {
                o.setType(rs.getString(2));
                o.setCost(rs.getFloat(3));
            }

            String sql3 = "SELECT * FROM shopbanhang.payment WHERE ID=" + orderID + ";";
            statement = conn.createStatement();
            rs = statement.executeQuery(sql3);

            while (rs.next()) {
                o.setStatus(rs.getString(3));
                o.setAmmount(rs.getFloat(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    @Override
    public ArrayList<OrderSTT> getAllOrder(int UserID) {
        String sql1 = "SELECT Shipment.Type,Shipment.Cost,shopbanhang.Order.CreatedDate,payment.status,payment.Amount\n"
                + "FROM ((shopbanhang.order\n"
                + "inner join shipment on shopbanhang.order.ShipmentID = shipment.ID)\n"
                + "inner join payment on shopbanhang.order.ID = payment.OrderID)\n"
                + "WHERE Shopbanhang.order.UserID =" + UserID + ";";
        ArrayList<OrderSTT> listorder = new ArrayList<>();
        Statement statement;
        ResultSet rs;

        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql1);

            while (rs.next()) {
                OrderSTT o = new OrderSTT();
                o.setType(rs.getString(1));
                o.setCost(rs.getFloat(2));
                o.setCreatedDate(rs.getDate(3));
                o.setStatus(rs.getString(4));
                o.setAmmount(rs.getFloat(5));
                listorder.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return listorder;
    }

    @Override
    public int getUserID(String phone, String mail) {
        String sql = "Insert into user (Phone,Mail)  values (?,?);";
        PreparedStatement prestatement;
        ResultSet rs;

        try {
            prestatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prestatement.setString(1, phone);
            prestatement.setString(2, mail);
            int rowcount = prestatement.executeUpdate();
            rs = prestatement.getGeneratedKeys();
            int idValue = 0;
            if (rs.next()) {
                idValue = rs.getInt(1);
            }
            return idValue;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public int updateCustomer(int id, String phone, String mail, String numberhouse, String street, String distinct, String city, String firstname, String midname, String lastname) {
//        String sql1 = "Update user\n"
//                + "set Phone=?,mail=?\n"
//                + "where id=?";
//        String sql2 = "Update address\n"
//                + "set Numberhouse=?,street=?,Distincts=?,city=?"
//                + "where Userid=?";
//        String sql3 = "Update fullname\n"
//                + "set firstname=?,midname=?,lastname=?\n"
//                + "where Userid=?";
//        
//        try {
//            prestatement = conn.prepareStatement(sql1);
//            prestatement.setString(1, phone);
//            prestatement.setString(2, mail);
//            prestatement.setInt(3, id);
//            int rowcount1 = prestatement.executeUpdate();
//            prestatement1 = conn.prepareStatement(sql2);
//            prestatement1.setString(1, numberhouse);
//            prestatement1.setString(2, street);
//            prestatement1.setString(3, distinct);
//            prestatement1.setString(4, city);
//            prestatement1.setInt(5, id);
//            int rowcount2 = prestatement1.executeUpdate();
//            prestatement2 = conn.prepareStatement(sql3);
//            prestatement2.setString(1, firstname);
//            prestatement2.setString(2, midname);
//            prestatement2.setString(3, lastname);
//            prestatement2.setInt(4, id);
//            int rowcount3 = prestatement2.executeUpdate();
//            return rowcount3;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            return 0;
//        }
        return 0;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_USER_BY_USERNAME_SQL)) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            System.out.println(username);

            if (rs.next()) {

                user = Mapper.mapUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }
    }

    @Override
    public User getUserById(int id) {
        User user = null;

        try (PreparedStatement ps = conn.prepareStatement(GET_USER_BY_USERID_SQL)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = Mapper.mapUser(rs);
            }
            
            System.out.println("User null ? " + (user == null));
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }
    }
}
