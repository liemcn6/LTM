/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.electronics;

import java.util.Map;
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
public interface ElectronicDAO {
    int deleteElectronic(int ID);
    int UpdateElectronics(Electronics electronics);
    int UpdateElectronicOrigin(ElectronicOrigin electronicOrigin);
    int UpdateElectronicsItem(ElectronicsItem electronicsItem);
    int UpdateLaptop(Laptop laptop);
    int UpdateMobilePhone(Mobilephone mobilephone);
    int UpdateTV(TV tv);
    Laptop getLaptop(int id);
    ElectronicsItem getElectronicsIT(int ID);
    Mobilephone getMobilephone(int id); 
    TV getTV(int id);
    ElectronicOrigin getElectronicORG(int id);
    Map<ElectronicsItem, Laptop> getLaptopAllStt(int ID);
    Map<ElectronicsItem, Mobilephone> getMobileAllStt(int ID);
    Map<ElectronicsItem, TV> getTVAllStt(int ID);
}
