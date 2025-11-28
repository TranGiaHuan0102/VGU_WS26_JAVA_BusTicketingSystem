package com.controller.java.tickets;

import java.time.LocalDate;

public class DailyTicket extends Ticket{
    private final LocalDate end_date;
    private final TICKET_TYPE ticket_type = TICKET_TYPE.DAILY;
    
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
    @Override
    public String getStringTicketType(){return this.ticket_type.toString();}
    @Override
    public LocalDate getExpiryDate(){return this.end_date;}
    public LocalDate getEndDate(){return this.end_date;}
}