/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.shoes;

import dao.utils.ConDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.shoes.Shoes;
import model.shoes.ShoesDesign;
import model.shoes.ShoesItem;
import model.shoes.ShoesOrigin;

/**
 *
 * @author DELL
 */
public class ShoesDAOImpl implements ShoesDAO {

    private final Connection conn;
    Statement Statement2;
    private final String sql1 = "Select ShoesDesignID from shopbanhang.shoes Where ID= ?; ";
    private final String sql2 = "Select ShoesOriginID from shopbanhang.shoes Where ID= ?;";
    private final String sql3 = "DELETE FROM shopbanhang.shoesdesign where ID = ?;";
    private final String sql4 = "DELETE FROM shopbanhang.shoesorigin where ID = ?;";
    private final String sql5 = "DELETE FROM shopbanhang.shoes where ID = ?;";
    private final String sql6 = "Update shoes SET Name=?,RemainingQuantity=?,Cost=?,Version=? where id=?";
    private final String sql7 = "Update shoesdesign SET Type=?,Material=?,Style=?,Model=?,Gender=?,Age=? where ID=?";
    private final String sql8 = "Update shoesitem SET Color=?,Size=? where ID=?";
    private final String sql9 = "Update shoesorigin SET CompanyName=?,Address=?,DateOfManfacture=? where ID=?";
    private final String sql10 = "SELECT * FROM shoes WHERE ID=?;";
    private final String sql11 = "SELECT * FROM shoesorigin WHERE ID=?;";
    private final String sql12 = "SELECT * FROM shoesdesign WHERE ID = ?;";
    private final String sql13 = "SELECT * FROM shoesitem WHERE ShoesID=?;";
    private final String sql14 = "SELECT * FROM item Where ID = ?;";

    public ShoesDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int deleteShoes(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        PreparedStatement prestatement2;
        PreparedStatement prestatement3;
        PreparedStatement prestatement4;
        ResultSet rs;

        try {
            prestatement = conn.prepareStatement(sql1);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            int ShoesDesignID = 0;
            while (rs.next()) {
                ShoesDesignID = rs.getInt(2);
            }
            prestatement1 = conn.prepareStatement(sql2);
            prestatement1.setInt(1, ID);
            rs = prestatement1.executeQuery();
            int ShoesOriginID = 0;
            while (rs.next()) {
                ShoesOriginID = rs.getInt(3);
            }

            prestatement2 = conn.prepareStatement(sql5);
            prestatement2.setInt(1, ID);
            int rowDeleted3 = prestatement2.executeUpdate();

            prestatement3 = conn.prepareStatement(sql3);
            prestatement3.setInt(1, ShoesDesignID);
            int rowDeleted = prestatement3.executeUpdate();

            prestatement4 = conn.prepareStatement(sql4);
            prestatement4.setInt(1, ShoesOriginID);
            int rowDeleted1 = prestatement4.executeUpdate();

            return rowDeleted3;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateShoes(Shoes shoes) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql6);
            prestatement.setString(1, shoes.getName());
            prestatement.setInt(2, shoes.getRemainingQuantity());
            prestatement.setFloat(3, shoes.getCost());
            prestatement.setString(4, shoes.getVersion());
            prestatement.setInt(5, shoes.getId());
            int rowcount1 = prestatement.executeUpdate();
            return rowcount1;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int updateShoesDesign(ShoesDesign shoesDesign) {

        //"Selecte * form shoes where id=?;
        try {
            PreparedStatement prestatement = conn.prepareStatement(sql7);
            prestatement.setString(1, shoesDesign.getType());
            prestatement.setString(2, shoesDesign.getMaterial());
            prestatement.setString(3, shoesDesign.getStyle());
            prestatement.setString(4, shoesDesign.getModel());
            prestatement.setString(5, shoesDesign.getGender());
            prestatement.setInt(6, shoesDesign.getAge());
            prestatement.setInt(7, shoesDesign.getId());
            int rowcount2 = prestatement.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int updateShoesItem(ShoesItem shoesItem) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql8);
            prestatement.setString(1, shoesItem.getColor());
            prestatement.setString(2, shoesItem.getSize());
            prestatement.setInt(3, shoesItem.getID());
            int rowcount2 = prestatement.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int updateShoesOrigin(ShoesOrigin shoesOrigin) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql9);
            prestatement.setString(1, shoesOrigin.getCompanyName());
            prestatement.setString(2, shoesOrigin.getAddress());
            prestatement.setString(3, shoesOrigin.getDateOfManufacture().toString());
            prestatement.setInt(4, shoesOrigin.getId());
            int rowcount4 = prestatement.executeUpdate();
            return rowcount4;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Shoes getShoes(int ID) {
        PreparedStatement prestatement;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql10);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            Shoes s = new Shoes();
            if (rs.next()) {
                s.setName(rs.getString(4));
                s.setRemainingQuantity(rs.getInt(5));
                s.setCost(rs.getFloat(6));
                s.setVersion(rs.getString(7));
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ShoesOrigin getShoesORG(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql10);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            int tmp = 0;
            if (rs.next()) {
                tmp = rs.getInt(3);
            }

            prestatement1 = conn.prepareStatement(sql11);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();
            ShoesOrigin so = new ShoesOrigin();
            if (rs.next()) {
                so.setCompanyName(rs.getString(2));
                so.setAddress(rs.getString(3));
                so.setDateOfManufacture(rs.getDate(4));
            }
            return so;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ShoesDesign getShoesDSG(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql10);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            int tmp = 0;
            if (rs.next()) {
                tmp = rs.getInt(2);
            }

            prestatement1 = conn.prepareStatement(sql12);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();
            ShoesDesign sd = new ShoesDesign();
            if (rs.next()) {
                sd.setType(rs.getString(2));
                sd.setMaterial(rs.getString(3));
                sd.setStyle(rs.getString(4));
                sd.setModel(rs.getString(5));
                sd.setGender(rs.getString(6));
                sd.setAge(rs.getInt(7));
            }
            return sd;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ShoesItem getShoesIT(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql13);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            ShoesItem si = new ShoesItem();
            int tmp = 0;
            if (rs.next()) {
                si.setColor(rs.getString(1));
                si.setSize(rs.getString(2));
                tmp = rs.getInt(3);
            }

            prestatement1 = conn.prepareStatement(sql14);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();

            if (rs.next()) {
                while (rs.next()) {
                    si.setDescription(rs.getString(2));
                    si.setPrice(rs.getFloat(3));
                    si.setDiscount(rs.getFloat(4));
                    si.setSellingStatus(rs.getString(5));
                    si.setImage(rs.getString(6));
                    si.setCategory(rs.getString(7));
                }
            }
            return si;
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Map<ShoesItem, Shoes> getShoesAllStt(int ID) {
        Map<ShoesItem, Shoes> tmp = new HashMap<ShoesItem, Shoes>();
        Shoes s = getShoes(ID);
        s.setSd(getShoesDSG(ID));
        s.setSo(getShoesORG(ID));
        tmp.put(getShoesIT(ID), s);
        return tmp;
    }
}
