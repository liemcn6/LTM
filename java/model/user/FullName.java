/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

/**
 *
 * @author Admin
 */
public class FullName {

    private int id;
    private String firstName;
    private String midName;
    private String lastName;

    public FullName() {
    }

    public FullName(int id, String firstName, String midName, String lastName) {
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMidName() {
        return midName;
    }

    public String getLastName() {
        return lastName;
    }

    public String standardizeName(String name) {
        return (name != null ? name + " " : "");
    }

    @Override
    public String toString() {
        String fullNameString = standardizeName(firstName) + standardizeName(midName) + standardizeName(lastName);
        return fullNameString.trim();
    }
}
