/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.clothes;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item.Item;
import model.clothes.Clothes;
import model.clothes.ClothesDesign;
import model.clothes.ClothesItem;
import model.clothes.ClothesOrigin;

/**
 *
 * @author DELL
 */
public class ClothesDAOImpl implements ClothesDAO {

    private final Connection conn;
    private final String sql1 = "Select ClothesDesignID from shopbanhang.clothes Where ID=? ;";
    private final String sql2 = "Select ClothesOriginID from shopbanhang.clothes Where ID=? ;";
    private final String sql3 = "DELETE FROM shopbanhang.clothesdesign where ID = ?;";
    private final String sql4 = "DELETE FROM shopbanhang.clothesorigin where ID = ?;";
    private final String sql5 = "DELETE FROM shopbanhang.clothes where ID = ?;";
    private final String sql6 = "Update clothes SET Name=?,RemainingQuantity=?,Cost=?,Version=? where ID=?";
    private final String sql7 = "Update clothesdesign SET Type=?,Material=?,Style=?,Model=?,Gender=?,Age=? where ID=?";
    private final String sql8 = "Update clothesitem SET Color=?,Size=? where ID=?";
    private final String sql14 = "Update clothesorigin SET CompanyName=?,Address=?,DateOfManufacture=? where ID=?";
    private final String sql9 = "SELECT * FROM clothes WHERE ID=?;";
    private final String sql10 = "SELECT * FROM clothesorigin WHERE ID=?;";
    private final String sql11 = "SELECT * FROM clothesdesign WHERE ID = ?;";
    private final String sql12 = "SELECT * FROM clothesitem WHERE ClothesID=?;";
    private final String sql13 = "SELECT * FROM item Where ID = ?;";

    public ClothesDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int deleteCLothes(int ID) {
        ResultSet rs;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement1;
        PreparedStatement preparedStatement2;
        PreparedStatement preparedStatement3;
        PreparedStatement PreparedStatement4;
        try {
            preparedStatement = conn.prepareStatement(sql1);
            preparedStatement.setInt(1, ID);
            rs = preparedStatement.executeQuery();
            int ClothesDesignID = 0;
            while (rs.next()) {
                ClothesDesignID = rs.getInt(1);
            }
            preparedStatement1 = conn.prepareStatement(sql2);
            preparedStatement1.setInt(1, ID);
            rs = preparedStatement1.executeQuery();
            int ClothesOriginID = 0;
            while (rs.next()) {
                ClothesOriginID = rs.getInt(1);
            }

            PreparedStatement prestatement2 = conn.prepareStatement(sql5);
            prestatement2.setInt(1, ID);
            int rowDeleted3 = prestatement2.executeUpdate();

            PreparedStatement prestatement3 = conn.prepareStatement(sql3);
            prestatement3.setInt(1, ClothesDesignID);
            int rowDeleted = prestatement3.executeUpdate();

            PreparedStatement prestatement4 = conn.prepareStatement(sql4);
            prestatement4.setInt(1, ClothesOriginID);
            int rowDeleted1 = prestatement4.executeUpdate();

            return rowDeleted3;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int UpdateClothes(Clothes clothes) {

        try {
            PreparedStatement prestatement = conn.prepareStatement(sql6);
            prestatement = conn.prepareStatement(sql6);
            prestatement.setString(1, clothes.getName());
            prestatement.setInt(2, clothes.getRemainingquantity());
            prestatement.setFloat(3, clothes.getCost());
            prestatement.setString(4, clothes.getVersion());
            prestatement.setInt(5, clothes.getId());
            int rowcount1 = prestatement.executeUpdate();
            return rowcount1;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateCLothesDesign(ClothesDesign clothesDesign) {

        try {
            PreparedStatement prestatement1 = conn.prepareStatement(sql7);
            prestatement1 = conn.prepareStatement(sql7);
            prestatement1.setString(1, clothesDesign.getType());
            prestatement1.setString(2, clothesDesign.getMaterial());
            prestatement1.setString(3, clothesDesign.getStyle());
            prestatement1.setString(4, clothesDesign.getModel());
            prestatement1.setString(5, clothesDesign.getGender());
            prestatement1.setInt(6, clothesDesign.getAge());
            prestatement1.setInt(7, clothesDesign.getId());
            int rowcount2 = prestatement1.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    @Override
    public int UpdateCLothesItem(ClothesItem clothesItem) {

        try {
            PreparedStatement prestatement2 = conn.prepareStatement(sql8);
            prestatement2 = conn.prepareStatement(sql8);
            prestatement2.setString(1, clothesItem.getColor());
            prestatement2.setString(2, clothesItem.getSize());
            prestatement2.setInt(3, clothesItem.getID());
            int rowcount4 = prestatement2.executeUpdate();
            return rowcount4;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateCLothesOrigin(ClothesOrigin clothesOrigin) {

        try {
            PreparedStatement prestatement3 = conn.prepareStatement(sql14);
            prestatement3 = conn.prepareStatement(sql14);
            prestatement3.setString(1, clothesOrigin.getCompanyName());
            prestatement3.setString(2, clothesOrigin.getAddress());
            prestatement3.setString(3, clothesOrigin.getDateOfManufacture().toString());
            prestatement3.setInt(4, clothesOrigin.getId());
            int rowcount3 = prestatement3.executeUpdate();
            return rowcount3;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    @Override
    public Clothes getClothes(int ID) {
        PreparedStatement prestatement;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql9);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            Clothes c = new Clothes();
            if (rs.next()) {
                c.setName(rs.getString(4));
                c.setRemainingQuantity(rs.getInt(5));
                c.setCost(rs.getFloat(6));
                c.setVersion(rs.getString(7));
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ClothesOrigin getClothesORG(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql9);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            int tmp = 0;
            if (rs.next()) {
                tmp = rs.getInt(3);
            }

            prestatement1 = conn.prepareStatement(sql10);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();
            ClothesOrigin co = new ClothesOrigin();
            if (rs.next()) {
                co.setCompanyName(rs.getString(2));
                co.setAddress(rs.getString(3));
                co.setDateOfManufacture(rs.getDate(4));
            }
            return co;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ClothesDesign getClothesDSG(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql9);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            int tmp = 0;
            if (rs.next()) {
                tmp = rs.getInt(2);
            }

            prestatement1 = conn.prepareStatement(sql11);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();
            ClothesDesign cd = new ClothesDesign();
            if (rs.next()) {
                cd.setType(rs.getString(2));
                cd.setMaterial(rs.getString(3));
                cd.setStyle(rs.getString(4));
                cd.setModel(rs.getString(5));
                cd.setGender(rs.getString(6));
                cd.setAge(rs.getInt(7));
            }
            return cd;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ClothesItem getClothesIT(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql12);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            ClothesItem ci = new ClothesItem();
            int tmp = 0;
            if (rs.next()) {
                ci.setColor(rs.getString(1));
                ci.setSize(rs.getString(2));
                tmp = rs.getInt(3);
            }

            prestatement1 = conn.prepareStatement(sql13);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();

            if (rs.next()) {
                Item item = Mapper.mapItem(rs);

                ci.setName(item.getName());
                ci.setDescription(item.getDescription());
                ci.setPrice(item.getPrice());
                ci.setDiscount(item.getDiscount());
                ci.setSellingStatus(item.getSellingStatus());
                ci.setImage(item.getImage());
                ci.setCategory(item.getCategory());
            }
            return ci;
        } catch (SQLException ex) {
            Logger.getLogger(ClothesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Map<ClothesItem, Clothes> getClothesAllStt(int ID) {
        Map<ClothesItem, Clothes> tmp = new HashMap<ClothesItem, Clothes>();
        Clothes c = getClothes(ID);
        c.setCd(getClothesDSG(ID));
        c.setCo(getClothesORG(ID));
        tmp.put(getClothesIT(ID), c);
        return tmp;
    }
}
