
package com.controller.java.users;


public abstract class User {
    private final String id;
    private final USER_TYPE user_type;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String password;
    private final double user_price_multiplier;
    
    // Enumeration for possible user types
    protected enum USER_TYPE{
        STUDENT,
        PROFESSOR
    }
    
    // Protected constructor
    protected User(String id, USER_TYPE user_type, String first_name, String last_name, String email, String password, double multiplier){
        this.id = id;
        this.user_type = user_type;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.user_price_multiplier = multiplier;
    }
    
    // Getters
    public String getID(){return this.id;}
    public String getStringUserType(){return this.user_type.toString();}
    public String getFirstName(){return this.first_name;}
    public String getLastName(){return this.last_name;}
    public String getEmailAddress(){return this.email;}
    public String getPassword(){return this.password;}
    public double getPriceMultiplier(){return this.user_price_multiplier;}
}
