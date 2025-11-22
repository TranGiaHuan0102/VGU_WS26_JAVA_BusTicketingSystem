package com.controller;

import com.database.DatabaseConnection;
import com.exceptions.*;
import com.java.tickets.Ticket;


public class DatabaseController {
    private DatabaseConnection db;
    
    // Controller constructor
    public DatabaseController() throws DatabaseConnectionException{
        db = new DatabaseConnection();
    }
    
    // Controller destructor
    public void close(){
        db.close();
    }
    
    // Insert
    public void insert(Ticket t) throws TicketInsertionException{
        db.insert(t);
    }
    
    // Search 
    public void search(String id){
        db.search(id);
    }
}
