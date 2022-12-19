/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Answer implements Serializable{
    static final long serialVersionUID = 32L;
    private Student student;
   
    public Answer() {
      
    }
    
    public Answer(final Student student) {
        
        this.student = student;
    }
    
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(final Student student) {
        this.student = student;
    }
}
