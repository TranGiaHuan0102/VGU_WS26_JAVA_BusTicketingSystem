package com.controller;

import java.util.List;

import com.database.DatabaseConnection;
import com.exceptions.*;
import com.java.tickets.Ticket;
import com.java.ticketdetails.TicketDetails;


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
    public List<TicketDetails> search(String id) throws TicketSelectionException{
        return db.search(id);
    }
    
    // Delete expired tickets
    public void delete_expired(String id) throws TicketDeletionException{
        db.delete_expired(id);
    }
}
