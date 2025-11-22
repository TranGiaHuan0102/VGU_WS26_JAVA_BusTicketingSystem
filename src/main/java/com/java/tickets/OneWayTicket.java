package com.java.tickets;

import java.time.LocalDate;
import java.time.LocalTime;


public class OneWayTicket extends Ticket{
    private final String direction;
    
    // Constructor
    OneWayTicket(String id, LocalDate date, String location_name, String direction){
        super(id, date, location_name);
        this.direction = direction;
    }
    
    // Class method
    public static OneWayTicket create(String id, LocalDate date, String location_name, String direction){
        OneWayTicket owt = new OneWayTicket(id, date, location_name, direction);
        return owt;
    }
    
    // Getters
    public String getDirection(){return this.direction;}
}


