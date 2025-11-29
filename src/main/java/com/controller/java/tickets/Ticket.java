package com.controller.java.tickets;

import java.time.LocalDate;


public abstract class Ticket{
    private final String id;
    private final LocalDate start_date;
    private final String location;
    private final TICKET_TYPE ticket_type;
    
    // Enum for ticket type
    protected enum TICKET_TYPE{
        WEEKLY,
        DAILY,
        ONEWAY
    }
    
    // Constructor
    protected Ticket(String id, TICKET_TYPE ticket_type, LocalDate start_date, String location){
        this.id = id;
        this.ticket_type = ticket_type;
        this.start_date = start_date;
        this.location = location;
    }
    
    // Getters:
    public String getID(){return this.id;}
    public String getStringTicketType(){return this.ticket_type.toString();}
    public LocalDate getStartDate(){return this.start_date;}
    public String getLocation(){return this.location;}
}