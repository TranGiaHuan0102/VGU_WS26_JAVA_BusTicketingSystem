package com.controller.java.users;


public class Student extends User {
    private final String Student_ID;
    
    // Public constructor
    public Student(String first_name, String last_name, String email, String password, String Student_ID){
        super(first_name, last_name, email, password);
        this.Student_ID = Student_ID;
    }
    
    // Getter
    public String getStudentID(){return this.Student_ID;}
}
