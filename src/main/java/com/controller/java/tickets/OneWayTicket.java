package com.controller.java.tickets;

import java.time.LocalDate;

public class OneWayTicket extends Ticket{
        private enum Direction{
            TO,
            FROM
        } 
        private final Direction direction;
    
    // Constructor
    private OneWayTicket(String id, TICKET_TYPE ticket_type, LocalDate date, String location_name, Direction d){
        super(id, ticket_type, date, location_name);
        this.direction = d;
    }
    
    // Class methods
    public static OneWayTicket create_FROM(String id, LocalDate date, String location_name){
        OneWayTicket owt = new OneWayTicket(id,  TICKET_TYPE.ONEWAY, date, location_name, Direction.FROM);
        return owt;
    }
    
    public static OneWayTicket create_TO(String id, LocalDate date, String location_name){
        OneWayTicket owt = new OneWayTicket(id, TICKET_TYPE.ONEWAY, date, location_name, Direction.TO);
        return owt;
    }

    public String getStringDirection(){return this.direction.toString();}
}


