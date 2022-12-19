package model;

import java.io.Serializable;

public class Student implements Serializable{
	static final long serialVersionUID = 1L;
	private String maSV;
	private String hovaten;
	private String IP;
	private int group;
	
	public Student(String maSV, String hovaten, String IP, int group) {
		this.maSV = maSV;
		this.hovaten = hovaten;
		this.IP = IP;
		this.group = group;
	}

	public String getMaSV() {
		return maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHovaten() {
		return hovaten;
	}

	public void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		IP = IP;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Student [serialVersionUID=" + serialVersionUID + ", maSV=" + maSV + ", hovaten=" + hovaten + ", IP="
				+ IP + ", group=" + group + "]";
	}
	
}