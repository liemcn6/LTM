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
public class Book {
    private int id;
    private String title;
    private String summary;
    private Date publicationDate;
    private int numberOfPage;
    private int RemainingQuantity;
    private boolean Status;
    private float cost;
    private Publisher pub;
    private ArrayList<Author> aut;
    public Book() {
    }

    public Book(int id, String title, String summary, Date publicationDate, int numberOfPage, int RemainingQuantity, boolean Status, float cost) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.publicationDate = publicationDate;
        this.numberOfPage = numberOfPage;
        this.RemainingQuantity = RemainingQuantity;
        this.Status = Status;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public int getRemainingQuantity() {
        return RemainingQuantity;
    }

    public void setRemainingQuantity(int RemainingQuantity) {
        this.RemainingQuantity = RemainingQuantity;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Publisher getPub() {
        return pub;
    }

    public void setPub(Publisher pub) {
        this.pub = pub;
    }

    public ArrayList<Author> getAut() {
        return aut;
    }

    public void setAut(ArrayList<Author> aut) {
        this.aut = aut;
    }


}
