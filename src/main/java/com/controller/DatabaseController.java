package com.controller;
import com.database.DatabaseConnection;


public class DatabaseController {
    private DatabaseConnection db;
    
    // Controller constructor
    public DatabaseController(){
        db = new DatabaseConnection();
    }
    
    // Controller destructor
    public void close(){
        db.close();
    }
}
