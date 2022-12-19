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
public class TV extends Electronics {

    private String screen;
    private String RAM;
    private String storage;
    private String connectedBoard;
    private String speaker;
    private String operatingSystem;
    private ElectronicOrigin eo;

    public TV() {
    }

    public TV(String screen, String RAM, String storage, String connectedBoard, String speaker, String operatingSystem) {
        this.screen = screen;
        this.RAM = RAM;
        this.storage = storage;
        this.connectedBoard = connectedBoard;
        this.speaker = speaker;
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

    public String getConnectedBoard() {
        return connectedBoard;
    }

    public void setConnectedBoard(String connectedBoard) {
        this.connectedBoard = connectedBoard;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
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
