package com.database.startup;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import com.tokuden.database.ConfigLoader;
//import com.tokuden.database.CRUD;


public class load_locations {
    public static void main(String args[]) throws Exception{
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // Initialize and populate Locations table
            //CRUD.createOperation(conn, "com/tokuden/sql/createLocations.sql");   /* Initialize Locations table */
            String locations_statement = "INSERT INTO Locations (Location_name) VALUES (?) ON CONFLICT DO NOTHING";
            //CRUD.updateOperation(conn, locations_statement, "com/tokuden/data/locations.csv");
            
            
            // Initialize and populate Ticket_Information table
            //CRUD.createOperation(conn, "com/tokuden/sql/createTicketInformation.sql");
            String ticket_info_statement = "INSERT INTO Ticket_Information "
                    + "(Location_name, Ticket_Type, Price, Morning_PickupTime, Afternoon_PickupTime)"
                    + " VALUES (?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
            //CRUD.updateOperation(conn, ticket_info_statement, "com/tokuden/data/ticket_info.csv");
            
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
    }    
}
