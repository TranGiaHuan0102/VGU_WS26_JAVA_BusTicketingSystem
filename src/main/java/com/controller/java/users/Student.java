package com.controller.java.users;


public class Student extends User {
    // Public constructor
    public Student(String student_id, String first_name, String last_name, String email, String password){
        super(student_id, USER_TYPE.STUDENT, first_name, last_name, email, password, 1.0);
    }
}
