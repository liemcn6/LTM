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
public class Laptop extends Electronics {

    private String monitor;
    private String RAM;
    private String card;
    private String storage;
    private String battery;
    private String CPU;
    private String hardware;
    private String touchpad;
    private String chip;
    private String operatingSystem;
    private ElectronicOrigin eo;

    public Laptop() {
    }

    public Laptop(String monitor, String RAM, String card, String storage, String battery, String CPU, String hardware, String touchpad, String chip, String operatingSystem) {
        this.monitor = monitor;
        this.RAM = RAM;
        this.card = card;
        this.storage = storage;
        this.battery = battery;
        this.CPU = CPU;
        this.hardware = hardware;
        this.touchpad = touchpad;
        this.chip = chip;
        this.operatingSystem = operatingSystem;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getTouchpad() {
        return touchpad;
    }

    public void setTouchpad(String touchpad) {
        this.touchpad = touchpad;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
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
