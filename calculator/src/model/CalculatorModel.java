/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class CalculatorModel {

    private long cong, tru, nhan, chia;

    public CalculatorModel() {
    }

    public CalculatorModel(long cong, long tru, long nhan, long chia) {
        this.cong = cong;
        this.tru = tru;
        this.nhan = nhan;
        this.chia = chia;
    }

    public void phepcong(long so1, long so2) {
        cong = so1 + so2;
    }

    public void pheptru(long so1, long so2) {
        tru = so1 - so2;
    }

    public void phepnhan(long so1, long so2) {
        nhan = so1 * so2;
    }

    public void phepchia(long so1, long so2) {
        chia = so1 / so2;
    }

    public long getCong() {
        return cong;
    }

    public long getTru() {
        return tru;
    }

    public long getNhan() {
        return nhan;
    }

    public long getChia() {
        return chia;
    }
    
}
