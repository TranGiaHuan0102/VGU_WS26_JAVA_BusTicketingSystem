
package com.controller.java.users;


public abstract class User {
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String password;
    
    // Enumeration for possible user types
    protected enum USER_TYPE{
        STUDENT,
        PROFESSOR
    }
    
    // Protected constructor
    protected User(String first_name, String last_name, String email, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }
    
    // Getters
    abstract public String getID();
    abstract public String getStringUserType();
    public String getFirstName(){return this.first_name;}
    public String getLastName(){return this.last_name;}
    public String getEmailAddress(){return this.email;}
    public String getPassword(){return this.password;}
}
