/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Item.Item;
import model.user.Account;
import model.user.Address;
import model.user.FullName;
import model.user.User;

/**
 *
 * @author Admin
 */
public class Mapper {

    public static Address mapAddress(ResultSet rs) throws SQLException {
        int id = rs.getInt("address.id");
        String houseNumber = rs.getString("address.numberhouse");
        String street = rs.getString("address.street");
        String district = rs.getString("address.district");
        String city = rs.getString("address.city");

        return new Address(id, houseNumber, street, district, city);
    }

    public static FullName mapFullName(ResultSet rs) throws SQLException {
        int id = rs.getInt("fullname.id");
        String firstName = rs.getString("fullname.firstname");
        String midName = rs.getString("fullname.midname");
        String lastName = rs.getString("fullname.lastname");

        return new FullName(id, firstName, midName, lastName);
    }

    public static Account mapAccount(ResultSet rs) throws SQLException {
        int id = rs.getInt("account.id");
        String username = rs.getString("account.username");
        String password = rs.getString("account.password");

        return new Account(id, username, password);
    }

    public static User mapUser(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user.id");
        String phoneNumber = rs.getString("user.phone");
        String email = rs.getString("user.mail");
        FullName fullName = mapFullName(rs);
        Address address = mapAddress(rs);
        Account account = mapAccount(rs);

        return new User(userId, phoneNumber, email, fullName, address, account);
    }
    
    public static Item mapItem(ResultSet rs) throws SQLException {
        int id = rs.getInt("item.id");
        String name = rs.getString("item.name");
        String description = rs.getString("item.description");
        float price = rs.getFloat("item.price");
        float discount = rs.getFloat("item.discount");
        String sellingStatus = rs.getString("item.sellingstatus");
        String image = rs.getString("item.image");
        String category = rs.getString("item.category");
        
        return new Item(id, name, description, price, discount, sellingStatus, image, category);
    }
}
