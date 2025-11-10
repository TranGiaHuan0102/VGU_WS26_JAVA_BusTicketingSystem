
package com.tokuden.database;

import com.tokuden.database.CRUD.CRUD_TicketInformation;
import com.tokuden.database.CRUD.CRUD_Locations;
import com.tokuden.database.CRUD.CRUD_Tickets;
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
            
            // Create and seed locations table
            CRUD_Locations.create_locations(conn);
            CRUD_Locations.seed_locations(conn);
            
            // Create and seed ticket info table
            CRUD_TicketInformation.create_ticket_info(conn);
            CRUD_TicketInformation.seed_ticket_info(conn);
            
            
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
