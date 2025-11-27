package com.controller.java.users;

/**
 *
 * @author tokuden
 */
public class Professor extends User {
    private final String Professor_ID;
    private final USER_TYPE user_type = USER_TYPE.PROFESSOR;
    
    // Public constructor
    public Professor(String first_name, String last_name, String email, String password, String Professor_ID){
        super(first_name, last_name, email, password);
        this.Professor_ID = Professor_ID;
    }
    
    // Getters
    @Override
    public String getID(){return this.Professor_ID;}
    
    @Override 
    public String getStringUserType(){return this.user_type.toString();}
}
