package com.tokuden.database.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;


public class CRUD_Locations {
    public static void create_locations(Connection conn) throws SQLException, IOException{
        try{
            CRUD.createOperation(conn, "com/tokuden/sql/createLocations.sql");   /* Initialize Locations table */
        }
        catch (IOException IOe){
            IOe.printStackTrace();
            System.err.println("Error finding SQL file!");
        }
        catch (SQLException SQLe){
            SQLe.printStackTrace();
            System.err.println("Error parsing SQL file!");
        }
    }
    
    
    // Default insertion (seeding database)
    public static void seed_locations(Connection conn) throws SQLException, IOException{
        try{
            String insert_statement = "INSERT INTO Locations (Location_name) VALUES (?) ON CONFLICT DO NOTHING";
            CRUD.insertOperation(conn, insert_statement, "com/tokuden/data/locations.csv");
        }
        catch(IOException IOe){
            IOe.printStackTrace();
            System.err.println("Error finding SQL file!");
        }
        catch (SQLException SQLe){
            SQLe.printStackTrace();
            System.err.println("Error parsing SQL file!");
        }
    }
}
