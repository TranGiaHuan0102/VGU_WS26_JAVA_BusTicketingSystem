package com.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnection {
    private Connection conn;
    
    // Connection constructor
    public DatabaseConnection(){
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        try{
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("I'm here boss!");
        }
        catch (SQLException SQLe){
            SQLe.printStackTrace();
        }
    }
    
    // Connection destructor
    public void close(){
        try{
            conn.close();
            System.out.println("I'm leaving, boss!");
        }
        catch(SQLException SQLe){
            SQLe.printStackTrace();
        }
    }
}
