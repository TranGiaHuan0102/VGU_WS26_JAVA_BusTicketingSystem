package com.controller.java.users;


public class Student extends User {
    private final String Student_ID;
    private final USER_TYPE user_type = USER_TYPE.STUDENT;
    
    // Public constructor
    public Student(String first_name, String last_name, String email, String password, String Student_ID){
        super(first_name, last_name, email, password);
        this.Student_ID = Student_ID;
    }
    
    // Getters
    @Override
    public String getID(){return this.Student_ID;}
    
    @Override
    public String getStringUserType(){return this.user_type.toString();}
}
