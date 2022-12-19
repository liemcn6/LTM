package socket.reserveStr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author ntkhanh
 */
public class Student implements Serializable{
       private String maSV;
       private String hovaten;
       private int nhom;
       static final long serialVersionUID =1L;

    public Student(String maSV, String hovaten, int nhom) {
        this.maSV = maSV;
        this.hovaten = hovaten;
        this.nhom = nhom;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getHovaten() {
        return hovaten;
    }

  
    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

	public int getNhom() {
		return nhom;
	}

	public void setNhom(int nhom) {
		this.nhom = nhom;
	}

   
}
