package com.database.CRUD;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import com.java.tickets.WeeklyTicket;

public class CRUD_Tickets {
    public static void create_week_daily(Connection conn) throws SQLException, IOException{
        try{
            CRUD.createOperation(conn, "com/database/sql/createWeekDailyTickets.sql");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void create_oneway(Connection conn) throws SQLException, IOException{
        try{
            CRUD.createOperation(conn, "com/database/sql/createOneWayTickets.sql");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // Overloading weekly insert
    public static void insert_ticket(Connection conn, WeeklyTicket wt){
        
    }
}
