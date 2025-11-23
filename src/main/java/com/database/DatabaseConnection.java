package com.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

import com.java.tickets.Ticket;
import com.java.ticketdetails.TicketDetails;
import com.database.CRUD.CRUD_Tickets;
import com.exceptions.*;

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
        CRUD_Tickets.insert_ticket(conn, t);
    }
    
    // Search tickets of ID
    public List<TicketDetails> search(String id) throws TicketSelectionException{
        return CRUD_Tickets.search_tickets(conn, id);
    }
    
    // Delete expired tickets
    public void delete_expired(String id) throws TicketDeletionException{
        CRUD_Tickets.delete_tickets(conn, id);
    }
}
