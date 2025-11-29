package com.controller.java.tickets;

import java.time.LocalDate;

public class LongTermTicket extends Ticket{
    private final LocalDate end_date;
   
    
    // Constructor
    private LongTermTicket(String id, TICKET_TYPE ticket_type, LocalDate start_date, String location_name){
        super(id, ticket_type, start_date, location_name);
        this.end_date = getStartDate().plusDays(180);
    }
    
    // Class methods
    public static LongTermTicket createWeekly(String id, LocalDate start_date, String location_name){
        LongTermTicket lt = new LongTermTicket(id, TICKET_TYPE.WEEKLY, start_date, location_name);
        return lt;
    }
    
    public static LongTermTicket createDaily(String id, LocalDate start_date, String location_name){
        LongTermTicket lt = new LongTermTicket(id, TICKET_TYPE.DAILY, start_date, location_name);
        return lt;
    }
    
    // Getter
    public LocalDate getExpiryDate(){return this.end_date;}
}


