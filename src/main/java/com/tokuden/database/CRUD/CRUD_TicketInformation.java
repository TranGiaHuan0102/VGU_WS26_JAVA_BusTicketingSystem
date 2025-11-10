package com.tokuden.database.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;

public class CRUD_TicketInformation {
    public static void create_ticket_info(Connection conn) throws SQLException, IOException{
        try{
            CRUD.createOperation(conn, "com/tokuden/sql/createTicketInformation.sql");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void seed_ticket_info(Connection conn) throws SQLException, IOException{
        try{
            String insert_statement = "INSERT INTO Ticket_Information "
                    + "(Location_name, Ticket_Type, Price, Morning_PickupTime, Afternoon_PickupTime)"
                    + " VALUES (?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";
            CRUD.insertOperation(conn, insert_statement, "com/tokuden/data/ticket_info.csv");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
