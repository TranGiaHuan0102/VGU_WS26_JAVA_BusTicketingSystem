package com.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import com.java.tickets.Ticket;
import com.java.tickets.WeeklyTicket;
import com.java.tickets.DailyTicket;
import com.java.tickets.OneWayTicket;

import com.database.CRUD.CRUD_Tickets;

public class test {
    public static void main(String args[]) {
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Good
            // WeeklyTicket wt = WeeklyTicket.create("10423043", "2025-11-20", "Becamex Tower");
            // CRUD_Tickets.insert_ticket(conn, wt);
            
            OneWayTicket owt = OneWayTicket.create("10423043", "2025-11-21", "Becamex Tower", "T");
            CRUD_Tickets.insert_ticket(conn, owt);
            
            conn.close();
        }
        catch (SQLException SQLe){
            SQLe.printStackTrace();
        }
    }
}
