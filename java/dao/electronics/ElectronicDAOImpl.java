/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.electronics;

import dao.utils.ConDB;
import dao.utils.Mapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item.Item;
import model.electrnoics.ElectronicOrigin;
import model.electrnoics.Electronics;
import model.electrnoics.ElectronicsItem;
import model.electrnoics.Laptop;
import model.electrnoics.Mobilephone;
import model.electrnoics.TV;

/**
 *
 * @author DELL
 */
public class ElectronicDAOImpl implements ElectronicDAO {

    private final Connection conn;
    private final String sql1 = "Select ElectronicOriginID from shopbanhang.electronics Where ID=? ";
    private final String sql2 = "DELETE FROM shopbanhang.electronicorigin where ID = ?;";
    private final String sql3 = "DELETE FROM laptop where ElectronicsID = ?;";
    private final String sql4 = "DELETE FROM mobilephone where ElectronicsID = ?;";
    private final String sql5 = "DELETE FROM tv where ElectronicsID = ?;";
    private final String sql6 = "DELETE FROM shopbanhang.electronics where ID = ?;";
    private final String sql7 = "Update electronics SET Name=?,Type=?,Version=?,Cost=?,RemainingQuantity=?,Size=?,Weight=? where ID=?";
    private final String sql8 = "Update electronicorigin SET CompanyName=?,Address=?,DateOfManufacture=?,Brand=? where ID=?";
    private final String sql9 = "Update electronicsitem SET Color=?,Insuarance=? where ID=?";
    private final String sql10 = "Update laptop SET Monitor=?,RAM=?,Card=?,Storage=?,Battery=?,CPU=?,Hardware=?,Touchpad=?,Chip=?,OperatingSystem=? Where ID= ?";
    private final String sql11 = "Update mobilephone SET Screen=?,RAM=?,Storage=?,Battery=?,ForwardCamera=?,BackwardCamerae=?,OperatingSystem=? Where ID= ?";
    private final String sql12 = "Update TV  SET Screen=?,RAM=?,Storage=?,OperatingSystem=?,ConnectedBoard=?,Speaker=? Where ID= ?";
    private final String sql13 = "SELECT * FROM electronics WHERE ID=?;";
    private final String sql14 = "SELECT * FROM laptop where ElectronicsID = ?;";
    private final String sql15 = "SELECT * FROM mobilephone WHERE ElectronicsID=?;";
    private final String sql16 = "SELECT * FROM tv WHERE ElectronicsID=?;";
    private final String sql17 = "SELECT * FROM electronicorigin where ID=?;";
    private final String sql18 = "SELECT * FROM item Where ID = ?;";

    public ElectronicDAOImpl() {
        conn = ConDB.getJDBCCOnection();
    }

    @Override
    public int deleteElectronic(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        PreparedStatement prestatement2;
        PreparedStatement prestatement3;
        PreparedStatement prestatement4;
        PreparedStatement prestatement5;
        PreparedStatement prestatement6;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql1);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            int ElectronicOriginID = 0;
            while (rs.next()) {
                ElectronicOriginID = rs.getInt(1);
            }

            prestatement5 = conn.prepareStatement(sql6);
            prestatement5.setInt(1, ID);
            int rowDeleted5 = prestatement5.executeUpdate();

            prestatement6 = conn.prepareStatement(sql2);
            prestatement6.setInt(1, ElectronicOriginID);
            int rowDeleted = prestatement6.executeUpdate();

            prestatement2 = conn.prepareStatement(sql3);
            prestatement2.setInt(1, ID);
            int rowDeleted2 = prestatement2.executeUpdate();

            prestatement3 = conn.prepareStatement(sql4);
            prestatement3.setInt(1, ID);
            int rowDeleted3 = prestatement2.executeUpdate();

            prestatement4 = conn.prepareStatement(sql5);
            prestatement4.setInt(1, ID);
            int rowDeleted4 = prestatement4.executeUpdate();

            return rowDeleted5;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int UpdateElectronics(Electronics electronics) {
        PreparedStatement prestatement;
        try {
            prestatement = conn.prepareStatement(sql7);
            prestatement.setString(1, electronics.getName());
            prestatement.setString(2, electronics.getType());
            prestatement.setString(3, electronics.getVersion());
            prestatement.setFloat(4, electronics.getCost());
            prestatement.setInt(5, electronics.getRemainingQuantity());
            prestatement.setString(6, electronics.getSize());
            prestatement.setString(7, electronics.getWeight());
            prestatement.setInt(8, electronics.getId());
            int rowcount1 = prestatement.executeUpdate();
            return rowcount1;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateElectronicOrigin(ElectronicOrigin electronicOrigin) {
        PreparedStatement prestatement;
        try {
            prestatement = conn.prepareStatement(sql8);
            prestatement.setString(1, electronicOrigin.getCompanyName());
            prestatement.setString(2, electronicOrigin.getAddress());
            prestatement.setString(3, electronicOrigin.getDateOfManufacture().toString());
            prestatement.setString(4, electronicOrigin.getBrand());
            prestatement.setInt(5, electronicOrigin.getId());
            int rowcount2 = prestatement.executeUpdate();
            return rowcount2;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateElectronicsItem(ElectronicsItem electronicsItem) {
        PreparedStatement prestatement;
        try {
            prestatement = conn.prepareStatement(sql9);
            prestatement.setString(1, electronicsItem.getColor());
            prestatement.setInt(2, electronicsItem.getInsuarance());
            prestatement.setInt(3, electronicsItem.getID());
            int rowcount3 = prestatement.executeUpdate();
            return rowcount3;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateLaptop(Laptop laptop) {
        PreparedStatement prestatement;
        try {
            prestatement = conn.prepareStatement(sql10);
            prestatement.setString(1, laptop.getMonitor());
            prestatement.setString(2, laptop.getRAM());
            prestatement.setString(3, laptop.getCard());
            prestatement.setString(4, laptop.getStorage());
            prestatement.setString(5, laptop.getBattery());
            prestatement.setString(6, laptop.getCPU());
            prestatement.setString(7, laptop.getHardware());
            prestatement.setString(8, laptop.getTouchpad());
            prestatement.setString(9, laptop.getChip());
            prestatement.setString(10, laptop.getOperatingSystem());
            prestatement.setInt(11, laptop.getId());
            int rowcount4 = prestatement.executeUpdate();
            return rowcount4;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateMobilePhone(Mobilephone mobilephone) {

        PreparedStatement prestatement;
        try {
            prestatement = conn.prepareStatement(sql11);
            prestatement.setString(1, mobilephone.getScreen());
            prestatement.setString(2, mobilephone.getRAM());
            prestatement.setString(3, mobilephone.getStorage());
            prestatement.setString(4, mobilephone.getBattery());
            prestatement.setString(5, mobilephone.getForwardCamera());
            prestatement.setString(6, mobilephone.getBackwardCamera());
            prestatement.setString(7, mobilephone.getOperatingSystem());
            prestatement.setInt(8, mobilephone.getId());
            int rowcount5 = prestatement.executeUpdate();
            return rowcount5;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int UpdateTV(TV tv) {
        PreparedStatement prestatement;
        try {
            prestatement = conn.prepareStatement(sql12);
            prestatement.setString(1, tv.getScreen());
            prestatement.setString(2, tv.getRAM());
            prestatement.setString(3, tv.getStorage());
            prestatement.setString(4, tv.getOperatingSystem());
            prestatement.setString(5, tv.getConnectedBoard());
            prestatement.setString(6, tv.getSpeaker());
            prestatement.setInt(7, tv.getId());
            int rowcount6 = prestatement.executeUpdate();
            return rowcount6;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public Laptop getLaptop(int id) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql13);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            Laptop l = new Laptop();
            if (rs.next()) {
                l.setName(rs.getString(3));
                l.setType(rs.getString(4));
                l.setVersion(rs.getString(5));
                l.setCost(rs.getFloat(6));
                l.setRemainingQuantity(rs.getInt(7));
                l.setSize(rs.getString(8));
                l.setWeight(rs.getString(9));
            }
            prestatement1 = conn.prepareStatement(sql14);
            prestatement1.setInt(1, id);
            rs = prestatement1.executeQuery();
            if (rs.next()) {
                l.setMonitor(rs.getString(1));
                l.setRAM(rs.getString(2));
                l.setCard(rs.getString(3));
                l.setStorage(rs.getString(4));
                l.setBattery(rs.getString(5));
                l.setCPU(rs.getString(6));
                l.setHardware(rs.getString(7));
                l.setTouchpad(rs.getString(8));
                l.setChip(rs.getString(9));
                l.setOperatingSystem(rs.getString(10));
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ElectronicsItem getElectronicsIT(int ID) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql13);
            prestatement.setInt(1, ID);
            rs = prestatement.executeQuery();
            ElectronicsItem ei = new ElectronicsItem();
            int tmp = 0;
            if (rs.next()) {
                ei.setColor(rs.getString(1));
                ei.setInsuarance(rs.getInt(2));
                tmp = rs.getInt(3);
            }

            prestatement1 = conn.prepareStatement(sql18);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();

            if (rs.next()) {
                Item item = Mapper.mapItem(rs);

                ei.setName(item.getName());
                ei.setDescription(item.getDescription());
                ei.setPrice(item.getPrice());
                ei.setDiscount(item.getDiscount());
                ei.setSellingStatus(item.getSellingStatus());
                ei.setImage(item.getImage());
                ei.setCategory(item.getCategory());
            }
            return ei;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Mobilephone getMobilephone(int id) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql13);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            Mobilephone l = new Mobilephone();
            if (rs.next()) {
                l.setName(rs.getString(3));
                l.setType(rs.getString(4));
                l.setVersion(rs.getString(5));
                l.setCost(rs.getFloat(6));
                l.setRemainingQuantity(rs.getInt(7));
                l.setSize(rs.getString(8));
                l.setWeight(rs.getString(9));
            }
            prestatement1 = conn.prepareStatement(sql15);
            prestatement1.setInt(1, id);
            rs = prestatement1.executeQuery();
            if (rs.next()) {
                l.setScreen(rs.getString(1));
                l.setRAM(rs.getString(2));
                l.setStorage(rs.getString(3));
                l.setBattery(rs.getString(4));
                l.setForwardCamera(rs.getString(5));
                l.setBackwardCamera(rs.getString(6));
                l.setOperatingSystem(rs.getString(7));
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public TV getTV(int id) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql13);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            TV l = new TV();
            if (rs.next()) {
                l.setName(rs.getString(3));
                l.setType(rs.getString(4));
                l.setVersion(rs.getString(5));
                l.setCost(rs.getFloat(6));
                l.setRemainingQuantity(rs.getInt(7));
                l.setSize(rs.getString(8));
                l.setWeight(rs.getString(9));
            }
            prestatement1 = conn.prepareStatement(sql16);
            prestatement1.setInt(1, id);
            rs = prestatement1.executeQuery();
            if (rs.next()) {
                l.setScreen(rs.getString(1));
                l.setRAM(rs.getString(2));
                l.setStorage(rs.getString(3));
                l.setOperatingSystem(rs.getString(4));
                l.setConnectedBoard(rs.getString(5));
                l.setSpeaker(rs.getString(6));
            }
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ElectronicOrigin getElectronicORG(int id) {
        PreparedStatement prestatement;
        PreparedStatement prestatement1;
        ResultSet rs;
        try {
            prestatement = conn.prepareStatement(sql13);
            prestatement.setInt(1, id);
            rs = prestatement.executeQuery();
            int tmp = 0;
            if (rs.next()) {
                tmp = rs.getInt(2);
            }
            prestatement1 = conn.prepareStatement(sql17);
            prestatement1.setInt(1, tmp);
            rs = prestatement1.executeQuery();
            ElectronicOrigin e = new ElectronicOrigin();
            if (rs.next()) {
                e.setCompanyName(rs.getString(2));
                e.setAddress(rs.getString(3));
                e.setDateOfManufacture(rs.getDate(4));
                e.setBrand(rs.getString(5));
            }
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(ElectronicDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Map<ElectronicsItem, Laptop> getLaptopAllStt(int ID) {
        Map<ElectronicsItem, Laptop> tmp = new HashMap<ElectronicsItem, Laptop>();
        Laptop l = getLaptop(ID);
        l.setEo(getElectronicORG(ID));
        tmp.put(getElectronicsIT(ID), l);
        return tmp;
    }

    @Override
    public Map<ElectronicsItem, Mobilephone> getMobileAllStt(int ID) {
        Map<ElectronicsItem, Mobilephone> tmp = new HashMap<ElectronicsItem, Mobilephone>();
        Mobilephone mb = getMobilephone(ID);
        mb.setEo(getElectronicORG(ID));
        tmp.put(getElectronicsIT(ID), mb);
        return tmp;
    }

    @Override
    public Map<ElectronicsItem, TV> getTVAllStt(int ID) {
        Map<ElectronicsItem, TV> tmp = new HashMap<ElectronicsItem, TV>();
        TV t = getTV(ID);
        t.setEo(getElectronicORG(ID));
        tmp.put(getElectronicsIT(ID), t);
        return tmp;
    }

}
