package com.java.tickets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class DailyTicket extends Ticket{
    private final LocalDate end_date;
    
    // Constructor
    DailyTicket(String id, LocalDate start_date, String location_name){ 
        super(id, start_date, location_name);
        this.end_date = getStartDate().plusDays(180);
        
    }
    
    public static DailyTicket create(String id, LocalDate start_date, String location_name){
        DailyTicket dt = new DailyTicket(id, start_date, location_name);
        return dt;
    }
    
    // Getters
    public LocalDate getEndDate(){return this.end_date;}
}