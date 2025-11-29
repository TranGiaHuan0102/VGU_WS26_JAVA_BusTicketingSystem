package com.controller.java.users;

/**
 *
 * @author tokuden
 */
public class Professor extends User {
    // Public constructor
    public Professor(String professor_id, String first_name, String last_name, String email, String password){
        super(professor_id, USER_TYPE.PROFESSOR, first_name, last_name, email, password, 0.5);
    }
}
