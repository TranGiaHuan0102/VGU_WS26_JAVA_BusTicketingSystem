package com.controller.java.tickets;

import java.time.LocalDate;

public class OneWayTicket extends Ticket{
    private enum Direction{
        TO,
        FROM
    } 
    
    private final TICKET_TYPE ticket_type = TICKET_TYPE.ONEWAY;
    private final Direction direction;
    
    // Constructor
    OneWayTicket(String id, LocalDate date, String location_name, Direction d){
        super(id, date, location_name);
        this.direction = d;
    }
    
    // Class methods
    public static OneWayTicket create_FROM(String id, LocalDate date, String location_name){
        OneWayTicket owt = new OneWayTicket(id, date, location_name, Direction.FROM);
        return owt;
    }
    
    public static OneWayTicket create_TO(String id, LocalDate date, String location_name){
        OneWayTicket owt = new OneWayTicket(id, date, location_name, Direction.TO);
        return owt;
    }
    
    // Getters
    @Override
    public String getStringTicketType(){return this.ticket_type.toString();}
    
    @Override 
    public LocalDate getExpiryDate(){return getStartDate();}
    
    public String getStringDirection(){return this.direction.toString();}
}


