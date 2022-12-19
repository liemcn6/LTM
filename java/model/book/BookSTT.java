/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.book;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class BookSTT {
    private String barcode;
    private float price;
    private float discount;
    private String sellingStatus;
    private String ISBN;
    private String title;
    private String sumary;
    private Date publicationDate;
    private int numberOfPage;
    private int quantityOfGood;
    private boolean status;
    private float cost;
    private String pnane;
    private String paddress;
    private ArrayList<String> aname;
    private ArrayList<String> abiography;
    private ArrayList<String> aemail;
    private ArrayList<String> aaddress;

    public BookSTT() {
    }

    public BookSTT(String barcode, float price, float discount, String sellingStatus, String ISBN, String title, String sumary, Date publicationDate, int numberOfPage, int quantityOfGood, boolean status, float cost, String pnane, String paddress, ArrayList<String> aname, ArrayList<String> abiography, ArrayList<String> aemail, ArrayList<String> aaddress) {
        this.barcode = barcode;
        this.price = price;
        this.discount = discount;
        this.sellingStatus = sellingStatus;
        this.ISBN = ISBN;
        this.title = title;
        this.sumary = sumary;
        this.publicationDate = publicationDate;
        this.numberOfPage = numberOfPage;
        this.quantityOfGood = quantityOfGood;
        this.status = status;
        this.cost = cost;
        this.pnane = pnane;
        this.paddress = paddress;
        this.aname = aname;
        this.abiography = abiography;
        this.aemail = aemail;
        this.aaddress = aaddress;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getSellingStatus() {
        return sellingStatus;
    }

    public void setSellingStatus(String sellingStatus) {
        this.sellingStatus = sellingStatus;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public int getQuantityOfGood() {
        return quantityOfGood;
    }

    public void setQuantityOfGood(int quantityOfGood) {
        this.quantityOfGood = quantityOfGood;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getPnane() {
        return pnane;
    }

    public void setPnane(String pnane) {
        this.pnane = pnane;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public ArrayList<String> getAname() {
        return aname;
    }

    public void setAname(ArrayList<String> aname) {
        this.aname = aname;
    }

    public ArrayList<String> getAbiography() {
        return abiography;
    }

    public void setAbiography(ArrayList<String> abiography) {
        this.abiography = abiography;
    }

    public ArrayList<String> getAemail() {
        return aemail;
    }

    public void setAemail(ArrayList<String> aemail) {
        this.aemail = aemail;
    }

    public ArrayList<String> getAaddress() {
        return aaddress;
    }

    public void setAaddress(ArrayList<String> aaddress) {
        this.aaddress = aaddress;
    }
    
}
