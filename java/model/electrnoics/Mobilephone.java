/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.electrnoics;

/**
 *
 * @author DELL
 */
public class Mobilephone extends Electronics {

    private String screen;
    private String RAM;
    private String storage;
    private String battery;
    private String forwardCamera;
    private String backwardCamera;
    private String operatingSystem;
    private ElectronicOrigin eo;

    public Mobilephone() {
    }

    public Mobilephone(String screen, String RAM, String storage, String battery, String forwardCamera, String backwardCamera, String operatingSystem) {
        this.screen = screen;
        this.RAM = RAM;
        this.storage = storage;
        this.battery = battery;
        this.forwardCamera = forwardCamera;
        this.backwardCamera = backwardCamera;
        this.operatingSystem = operatingSystem;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getForwardCamera() {
        return forwardCamera;
    }

    public void setForwardCamera(String forwardCamera) {
        this.forwardCamera = forwardCamera;
    }

    public String getBackwardCamera() {
        return backwardCamera;
    }

    public void setBackwardCamera(String backwardCamera) {
        this.backwardCamera = backwardCamera;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public ElectronicOrigin getEo() {
        return eo;
    }

    public void setEo(ElectronicOrigin eo) {
        this.eo = eo;
    }

}
