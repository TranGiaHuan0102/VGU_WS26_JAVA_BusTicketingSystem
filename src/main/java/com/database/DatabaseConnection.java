package com.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Map;
import java.util.List;

import com.database.CRUD.*;
import com.exceptions.*;

import com.controller.java.tickets.Ticket;
import com.controller.java.tickets.OneWayTicket;
import com.controller.java.users.User;


public class DatabaseConnection {
    private Connection conn;
    
    // Connection constructor
    public DatabaseConnection() throws DatabaseConnectionException {
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        try{
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("I'm here boss!");
        }
        catch (SQLException SQLe){
            throw new DatabaseConnectionException("Error: Unable to establish connection to database!");
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
    
    // Insert ticket
    public void insert(Ticket t) throws TicketInsertionException{
        if (t instanceof OneWayTicket){
            CRUD_Tickets.insert_ticket(conn, (OneWayTicket) t);
        }
        else{
            CRUD_Tickets.insert_ticket(conn, t);
        }
    }
    
    // Insert user
    public void insert(User u) throws NewUserException{
        CRUD_Users.insert_user(conn, u);
    }
    
    // Search tickets of ID
    public Map<String, List> search(String id) throws TicketSelectionException{
        return CRUD_Tickets.search_tickets(conn, id);
    }
    
    public User search_user(String id) throws UserSelectionException, LogInException{
        return CRUD_Users.search_user(conn, id);
    }
    
    // Delete expired tickets
    public void delete_expired(String id) throws TicketDeletionException{
        CRUD_Tickets.delete_tickets(conn, id);
    }
}
