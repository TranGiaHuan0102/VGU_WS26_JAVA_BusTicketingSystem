package com.database;

import com.database.CRUD.CRUD_TicketInformation;
import com.database.CRUD.CRUD_Locations;
import com.database.CRUD.CRUD_Tickets;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;


public class DatabaseConnection {
    public static void main(String args[]) {
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Create ticket tables
            CRUD_Tickets.create_week_daily(conn);
            CRUD_Tickets.create_oneway(conn);
            
            conn.close();
        }
        catch (SQLException SQLe){
            SQLe.printStackTrace();
        }
        catch (IOException IOe){
            IOe.printStackTrace();
        }
    }
}
