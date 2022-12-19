package model;

import java.io.Serializable;


public class Answer implements Serializable {
    static final long serialVersionUID = 2L;
    private Student student;
    boolean isAlreadyRegistration = false;
   
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
