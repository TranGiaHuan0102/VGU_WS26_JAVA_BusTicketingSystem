package com.database.CRUD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;

import com.java.tickets.WeeklyTicket;
import com.java.tickets.DailyTicket;
import com.java.tickets.OneWayTicket;

import java.time.LocalDate;


public class CRUD_Tickets {    
    

    // Overloading Insert
    
    private static boolean eligible(Connection conn, String id){
    // Can only re-register if current daily/weekly ticket expires
    boolean eligible = true;
        
    String selectStmt = "SELECT end_date FROM weekly_daily WHERE id = ? ORDER BY end_date DESC LIMIT 1";
    try (PreparedStatement stmt = conn.prepareStatement(selectStmt)){
        stmt.setString(1, id);
        
        ResultSet rSet = stmt.executeQuery();
        if (rSet.next()){
            LocalDate endDate = rSet.getDate("end_date").toLocalDate();
            if (endDate.isAfter(LocalDate.now())){
                eligible = false;
            }
        }
        
    }
    catch(SQLException sqle){
        System.out.println("Check failed: " + sqle.getMessage());
        sqle.printStackTrace();
        eligible = false; // default to not eligible on error
        }
    return eligible;
    }
    
    public static void insert_ticket(Connection conn, WeeklyTicket wt) {
    // Eligibility check
    if (!eligible(conn, wt.getID())){
        System.out.println("Cannot register: User already has an active ticket!");
        return;
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
        System.out.println("Insert failed: " + sqle.getMessage());
        sqle.printStackTrace();
        }
    }
    
    public static void insert_ticket(Connection conn, DailyTicket dt){
    // Eligibility check
    if (!eligible(conn, dt.getID())){
        System.out.println("Cannot register: User already has an active ticket!");
        return;
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
        System.out.println("Insert failed: " + sqle.getMessage());
        sqle.printStackTrace();
        }
    }
    
    public static void insert_ticket(Connection conn, OneWayTicket owt){
    String insertStmt = "INSERT INTO oneway (id, purchase_date, location_name, direction) VALUES(?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conn.prepareStatement(insertStmt)){
        stmt.setString(1, owt.getID());
        stmt.setDate(2, Date.valueOf(owt.getStartDate()));
        stmt.setString(3, owt.getLocation());
        stmt.setString(4, owt.getDirection());
        
        stmt.executeUpdate();
    }
    catch(SQLException sqle){
        System.out.println("Insert failed: " + sqle.getMessage());
        sqle.printStackTrace();
        }
    }
}
