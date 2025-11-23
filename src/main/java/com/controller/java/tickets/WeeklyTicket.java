package com.controller.java.tickets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class WeeklyTicket extends Ticket{
    private final LocalDate end_date; 
    
    // Constructor
    private WeeklyTicket(String id, LocalDate start_date, String location_name){
        super(id, start_date, location_name);
        this.end_date = getStartDate().plusDays(180);
    }
    
    // Class method
    public static WeeklyTicket create(String id, LocalDate start_date, String location_name){
        WeeklyTicket wt = new WeeklyTicket(id, start_date, location_name);
        return wt;
    }
    
    // Getters
    public LocalDate getEndDate(){return this.end_date;}
}


