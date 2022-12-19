package model;

import java.io.Serializable;

public class danhBa implements Serializable{
	private int id;
    private String name, sdt,diachi;
    
    
	public danhBa() {
		super();
	}


	public danhBa(int id, String name, String sdt, String diachi) {
		super();
		this.id = id;
		this.name = name;
		this.sdt = sdt;
		this.diachi = diachi;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public String getDiachi() {
		return diachi;
	}


	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
    
	@Override
    public String toString() {
        return "danhBa{" + "id=" + id + ", name=" + name + ", SDT=" + sdt + ",dia chi="+ diachi +'}';
    }
}
