package com.database.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;

import java.util.List;
import java.util.ArrayList;

import com.java.tickets.*;
import com.java.ticketdetails.*;
import com.exceptions.*;

import java.time.LocalDate;
import java.time.LocalTime;


public class CRUD_Tickets {    
    

    // Overloading Insert
    public static void insert_ticket(Connection conn, Ticket t) throws TicketInsertionException{
        if (t instanceof WeeklyTicket){
            insert_ticket(conn, (WeeklyTicket) t);
        }
        else if (t instanceof DailyTicket){
            insert_ticket(conn, (DailyTicket) t);
        }
        else if (t instanceof OneWayTicket){
            insert_ticket(conn, (OneWayTicket) t);
        }
    }
    
    private static void insert_ticket(Connection conn, WeeklyTicket wt) throws TicketInsertionException {
    // Eligibility check
    if (!CRUD_Helpers.eligible(conn, wt.getID())){
        throw new TicketInsertionException("User already has an active long-term ticket!");
    }
        
    String insertStmt = "INSERT INTO weekly_daily (id, start_date, end_date, ticket_type, location_name) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(insertStmt)) {
        stmt.setString(1, wt.getID());
        stmt.setDate(2, Date.valueOf(wt.getStartDate()));
        stmt.setDate(3, Date.valueOf(wt.getEndDate()));
        stmt.setString(4, "WEEKLY");
        stmt.setString(5, wt.getLocation());

        stmt.executeUpdate();
    } 
    
    catch (SQLException sqle) {
        throw new TicketInsertionException(sqle.getMessage());
        }
    }
    
    private static void insert_ticket(Connection conn, DailyTicket dt) throws TicketInsertionException{
    // Eligibility check
    if (!CRUD_Helpers.eligible(conn, dt.getID())){
        throw new TicketInsertionException("User already has an active long-term ticket!");
    }
        
    String insertStmt = "INSERT INTO weekly_daily (id, start_date, end_date, ticket_type, location_name) VALUES (?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(insertStmt)) {
        stmt.setString(1, dt.getID());
        stmt.setDate(2, Date.valueOf(dt.getStartDate()));
        stmt.setDate(3, Date.valueOf(dt.getEndDate()));
        stmt.setString(4, "DAILY");
        stmt.setString(5, dt.getLocation());

        stmt.executeUpdate();
    }
    catch (SQLException sqle) {
        throw new TicketInsertionException(sqle.getMessage());
        }
    }
    
    private static void insert_ticket(Connection conn, OneWayTicket owt) throws TicketInsertionException{
    String insertStmt = "INSERT INTO oneway (id, purchase_date, location_name, direction) VALUES(?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(insertStmt)){
        stmt.setString(1, owt.getID());
        stmt.setDate(2, Date.valueOf(owt.getStartDate()));
        stmt.setString(3, owt.getLocation());
        stmt.setString(4, owt.getDirection());
        
        stmt.executeUpdate();
    }
    catch(SQLException sqle){
        throw new TicketInsertionException(sqle.getMessage());
        }
    }
    
    public static List<TicketDetails> search_tickets(Connection conn, String id) throws TicketSelectionException{
    
    // Dynamic list of tickets
    List<TicketDetails> ticket_details = new ArrayList<>();
    
    String weekly_daily_Stmt = "SELECT W.start_date, W.end_date, TI.ticket_type, TI.location_name, TI.price, TI.morning_pickuptime, TI.afternoon_pickuptime "
                         + "FROM weekly_daily W "
                         + "JOIN ticket_information TI ON (W.ticket_type = TI.ticket_type AND W.location_name = TI.location_name) "
                         + "WHERE W.id = ?";
    
    String oneway_Stmt = "SELECT OW.purchase_date, OW.direction, TI.ticket_type, TI.location_name, OW.price, TI.morning_pickuptime, TI.afternoon_pickuptime "
                       + "FROM oneway OW "
                       + "JOIN ticket_information TI ON (OW.ticket_type = TI.ticket_type AND OW.location_name = TI.location_name) "
                       + "WHERE OW.id = ?";
    
    // Query long-term tickets
    try (PreparedStatement stmt = conn.prepareStatement(weekly_daily_Stmt)){
        stmt.setString(1, id);
        ResultSet WD_rSet = stmt.executeQuery();
        
        while(WD_rSet.next()){
            LocalDate start_date = WD_rSet.getDate("start_date").toLocalDate();
            String location = WD_rSet.getString("location_name");  
            long price = WD_rSet.getLong("price");
            LocalDate end_date = WD_rSet.getDate("end_date").toLocalDate();
            String type = WD_rSet.getString("ticket_type");
            LocalTime morning_pickuptime = WD_rSet.getTime("morning_pickuptime").toLocalTime();
            LocalTime afternoon_pickuptime = WD_rSet.getTime("afternoon_pickuptime").toLocalTime();
            
            ticket_details.add(new WeeklyDailyDetails(id, start_date, location, price, end_date, type, morning_pickuptime, afternoon_pickuptime));
        }
    }
    catch (SQLException sqle){
        throw new TicketSelectionException(sqle.getMessage());
    }
    
    // Query one way tickets
    try (PreparedStatement stmt = conn.prepareStatement(oneway_Stmt)){
        stmt.setString(1, id);
        ResultSet OW_rSet = stmt.executeQuery();
        
        while(OW_rSet.next()){
            LocalDate start_date = OW_rSet.getDate("purchase_date").toLocalDate();
            String location = OW_rSet.getString("location_name");  
            long price = OW_rSet.getLong("price");
            String direction = OW_rSet.getString("direction");
            LocalTime pickuptime = (direction.equals("T")) ? OW_rSet.getTime("morning_pickuptime").toLocalTime() : OW_rSet.getTime("afternoon_pickuptime").toLocalTime();  
            
            ticket_details.add(new OneWayDetails(id, start_date, location, price, direction, pickuptime));
        }
    }
    catch(SQLException sqle){
        throw new TicketSelectionException(sqle.getMessage());
    }
    
    return ticket_details;
    }
    
    public static void delete_tickets(Connection conn, String id) throws TicketDeletionException{
        String delete_weekly_daily_Stmt = "DELETE FROM weekly_daily WHERE (ID = ? AND end_date < CURRENT_DATE)";
        
        String delete_oneway_Stmt = "DELETE FROM oneway WHERE (ID = ? AND purchase_date < CURRENT_DATE)";
        
        // Delete from WEEKLY_DAILY
        try(PreparedStatement stmt = conn.prepareStatement(delete_weekly_daily_Stmt)){
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException sqle){
            throw new TicketDeletionException(sqle.getMessage());
        }
        
        // Delete from ONEWAY
        try(PreparedStatement stmt = conn.prepareStatement(delete_oneway_Stmt)){
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException sqle){
            throw new TicketDeletionException(sqle.getMessage());
        }
    }
}
