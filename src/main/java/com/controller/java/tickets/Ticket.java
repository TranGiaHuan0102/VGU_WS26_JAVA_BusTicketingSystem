package com.controller.java.tickets;

import java.time.LocalDate;


public abstract class Ticket{
    private final String id;
    private final LocalDate start_date;
    private final String location;
    
    // Enum for ticket type
    protected enum TICKET_TYPE{
        WEEKLY,
        DAILY,
        ONEWAY
    }
    
    // Constructor
    protected Ticket(String id, LocalDate start_date, String location){
        this.id = id;
        this.start_date = start_date;
        this.location = location;
    }
    
    // Getters:
    public String getID(){return this.id;}
    public LocalDate getStartDate(){return this.start_date;}
    public String getLocation(){return this.location;}
    
    // Abstract getters:
    public abstract String getStringTicketType();
    public abstract LocalDate getExpiryDate();
}