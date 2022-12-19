/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.item;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item.Item;

/**
 *
 * @author ADMIN
 */
public class ItemDAOImpl implements ItemDAO {

    private final Connection conn;
    private final String sql1 = "DELETE FROM shopbanhang.item where ID = ?;";
    private final String sql2 = "Update item SET Description=?,Price=?,Discount=?,SellingStatus=?,Image=?,Category=? where ID=?";
    private final String sql3 = "SELECT * FROM item WHERE ID = ?;";
    private final String GET_NEW_ITEMS_LIMIT_SQL = "SELECT * FROM item WHERE id < ? ORDER BY id DESC LIMIT ?";
    private final String GET_NEW_ITEMS_LIMIT_BY_CATEGORY_SQL = "SELECT * FROM item WHERE id <= ? AND category = ? ORDER BY id DESC LIMIT ?";

    public ItemDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int deleteItem(int id) {
        PreparedStatement prestatement;
        ResultSet rs;

        try {
            prestatement = conn.prepareStatement(sql1);
            prestatement.setInt(1, id);
            int rowDeleted = prestatement.executeUpdate();
            return rowDeleted;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public int updateItem(Item item) {
        PreparedStatement prestatement;

        try {
            prestatement = conn.prepareStatement(sql2);
            prestatement.setString(1, item.getDescription());
            prestatement.setFloat(2, item.getPrice());
            prestatement.setFloat(3, item.getDiscount());
            prestatement.setString(4, item.getSellingStatus());
            prestatement.setString(5, item.getImage());
            prestatement.setString(6, item.getCategory());
            prestatement.setInt(7, item.getID());
            int rowcount1 = prestatement.executeUpdate();
            return rowcount1;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Item getItem(int id) {
        PreparedStatement preStatement;
        ResultSet rs;
        try {
            preStatement = conn.prepareStatement(sql3);
            preStatement.setInt(1, id);
            rs = preStatement.executeQuery();
            Item i = new Item();
            while (rs.next()) {
                i.setName(rs.getString("name"));
                i.setDescription(rs.getString(2));
                i.setPrice(rs.getFloat(3));
                i.setDiscount(rs.getFloat(4));
                i.setSellingStatus(rs.getString(5));
                i.setImage(rs.getString(6));
                i.setCategory(rs.getString(7));
            }
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Item> getNewItems(int limit, int from) {
        List<Item> listItem = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_NEW_ITEMS_LIMIT_SQL)) {
            ps.setInt(1, limit);
            ps.setInt(2, from);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = Mapper.mapItem(rs);
                listItem.add(item);
            }

            System.out.println("List item length " + listItem.size());
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listItem;
        }
    }

    @Override
    public List<Item> getNewItemsByCategory(int limit, int from, String category) {
        List<Item> listItem = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(GET_NEW_ITEMS_LIMIT_BY_CATEGORY_SQL)) {
            ps.setInt(1, from);
            ps.setString(2, category);
            ps.setInt(3, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = Mapper.mapItem(rs);
                listItem.add(item);
            }

            System.out.println("List item length " + listItem.size());
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listItem;
        }
    }
}
