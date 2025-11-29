package com.database.CRUD;


import com.controller.java.tickets.*;
import com.controller.java.ticketdetails.*;
import com.exceptions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;
import java.time.LocalTime;

public class CRUD_Tickets {    
    

    // Overloading Inserts:
    
    // Long-term insert
    public static void insert_ticket(Connection conn, LongTermTicket lt) throws TicketInsertionException{        
        // Eligibility check
        if (!CRUD_TicketHelpers.eligible(conn, lt.getID())){
            throw new TicketInsertionException("User already has an active long-term ticket!");
        }
        
        String insertStmt = "INSERT INTO public.longterm(id, start_date, end_date, ticket_type, location_name) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(insertStmt)) {
            stmt.setString(1, lt.getID());
            stmt.setDate(2, Date.valueOf(lt.getStartDate()));
            stmt.setDate(3, Date.valueOf(lt.getExpiryDate()));
            stmt.setString(4, lt.getStringTicketType());
            stmt.setString(5, lt.getLocation());

            stmt.executeUpdate();
        }
        catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            throw new TicketInsertionException();
        }
    }
    
    // One-way insert
    public static void insert_ticket(Connection conn, OneWayTicket owt) throws TicketInsertionException{
        String insertStmt = "INSERT INTO public.oneway(id, departure_date, location_name, ticket_type, direction) VALUES (?, ?, ?, ?, ?::direction_type)";

        try (PreparedStatement stmt = conn.prepareStatement(insertStmt)){
            stmt.setString(1, owt.getID());
            stmt.setDate(2, Date.valueOf(owt.getStartDate()));
            stmt.setString(3, owt.getLocation());
            stmt.setString(4, owt.getStringTicketType());
            stmt.setString(5, owt.getStringDirection());

            stmt.executeUpdate();
        }
        catch(SQLException sqle){
            System.out.println(sqle.getMessage());
            throw new TicketInsertionException("Only support 2 oneways per day!");
            }
    }
    
    // Return a map of 2 lists: One being long-term ticket details, the other being oneway
    public static Map<String, List> search_tickets(Connection conn, String id) throws TicketSelectionException{
    
    // Separate the two types into two different array lists
    List<TicketDetails> longterm_details = new ArrayList<>();
    List<TicketDetails> oneway_details = new ArrayList<>(); 
    
    // Then combine them into one hashmap
    Map<String, List> ticket_details = new HashMap<>();
    
    
    String longterm_Stmt = "SELECT W.start_date, W.end_date, W.actual_price, W.ticket_type, TI.location_name, TI.morning_pickuptime, TI.afternoon_pickuptime "
                         + "FROM public.longterm W "
                         + "JOIN public.ticket_information TI ON (W.ticket_type = TI.ticket_type AND W.location_name = TI.location_name) "
                         + "WHERE W.id = ?";
    
    String oneway_Stmt = "SELECT OW.departure_date, OW.direction, OW.actual_price, OW.ticket_type, TI.location_name, TI.morning_pickuptime, TI.afternoon_pickuptime "
                       + "FROM public.oneway OW "
                       + "JOIN public.ticket_information TI ON (OW.ticket_type = TI.ticket_type AND OW.location_name = TI.location_name) "
                       + "WHERE OW.id = ? "
                       + "ORDER BY OW.departure_date ASC, OW.direction ASC";
    
    // Query long-term tickets
    try (PreparedStatement stmt = conn.prepareStatement(longterm_Stmt)){
        stmt.setString(1, id);
        ResultSet WD_rSet = stmt.executeQuery();
        
        while(WD_rSet.next()){
            LocalDate start_date = WD_rSet.getDate("start_date").toLocalDate();
            String location = WD_rSet.getString("location_name");  
            long price = WD_rSet.getLong("actual_price");
            String type = WD_rSet.getString("ticket_type");
            LocalDate end_date = WD_rSet.getDate("end_date").toLocalDate();
            LocalTime morning_pickuptime = WD_rSet.getTime("morning_pickuptime").toLocalTime();
            LocalTime afternoon_pickuptime = WD_rSet.getTime("afternoon_pickuptime").toLocalTime();
            
            longterm_details.add(new LongTermDetails(id, start_date, location, price, type, end_date, morning_pickuptime, afternoon_pickuptime));
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
            LocalDate departure_date = OW_rSet.getDate("departure_date").toLocalDate();
            String location = OW_rSet.getString("location_name");  
            long price = OW_rSet.getLong("actual_price");
            String type = OW_rSet.getString("ticket_type");
            String direction = OW_rSet.getString("direction");
            LocalTime pickuptime = (direction.equals("TO")) ? OW_rSet.getTime("morning_pickuptime").toLocalTime() : OW_rSet.getTime("afternoon_pickuptime").toLocalTime();  
            
            oneway_details.add(new OneWayDetails(id, departure_date, location, price, type, direction, pickuptime));
        }
    }
    catch(SQLException sqle){
        throw new TicketSelectionException(sqle.getMessage());
    }
    
    // Add to hashmap
    ticket_details.put("LONGTERM", longterm_details);
    ticket_details.put("ONEWAY", oneway_details);
    
    return ticket_details;
    }
}
