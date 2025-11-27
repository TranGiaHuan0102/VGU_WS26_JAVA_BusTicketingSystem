package com.controller.database;

import java.util.Map;
import java.util.List;

import com.database.DatabaseConnection;
import com.exceptions.*;
import com.controller.java.tickets.Ticket;
import com.controller.java.users.User;


public class DatabaseController {
    private final DatabaseConnection db;
    
    // Controller constructor
    public DatabaseController() throws DatabaseConnectionException{
        db = new DatabaseConnection();
    }
    
    // Controller destructor
    public void close(){
        db.close();
    }
    
    // Insert ticket
    public void insert(Ticket t) throws TicketInsertionException{
        db.insert(t);
    }
    
    // Insert user
    public void insert(User u) throws NewUserException{
        db.insert(u);
    }
    
    // Search ticket with ID
    public Map<String, List> search(String id) throws TicketSelectionException{
        return db.search(id);
    }
    
    // Search user with ID and password
    public User search_user(String id) throws UserSelectionException, LogInException{
        return db.search_user(id);
    }
    
    // Delete expired tickets
    public void delete_expired(String id) throws TicketDeletionException{
        db.delete_expired(id);
    }
}
