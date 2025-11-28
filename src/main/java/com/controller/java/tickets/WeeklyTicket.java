package com.controller.java.tickets;

import java.time.LocalDate;

public class WeeklyTicket extends Ticket{
    private final LocalDate end_date;
    private final TICKET_TYPE ticket_type = TICKET_TYPE.WEEKLY;
    
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
    @Override
    public String getStringTicketType(){return this.ticket_type.toString();}
    @Override
    public LocalDate getExpiryDate(){return this.end_date;}
}


