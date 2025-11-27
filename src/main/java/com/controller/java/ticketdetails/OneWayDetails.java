package com.controller.java.ticketdetails;

/**
 *
 * @author tokuden
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class OneWayDetails extends TicketDetails{
    private final String type = "ONE WAY";
    private final String direction;
    private final LocalTime pickuptime;
    
    public OneWayDetails(String id, LocalDate start_date, String location, String direction, LocalTime pickuptime){
        super(id, start_date, location, 150000);
        this.direction = direction;
        this.pickuptime = pickuptime;
    }
    
    // Getters
    protected String getType(){return this.type;}
    protected LocalTime getTime(){return this.pickuptime;}
    protected String getStringTime(){return this.pickuptime.toString();}
    protected String getDirection(){return this.direction;}
    
    @Override
    public final List<String> returnTicketDetail(){
        List<String> ticket_detail = new ArrayList<>();
        ticket_detail.add(getID());
        ticket_detail.add(getType());
        ticket_detail.add(getStringStartDate());
        ticket_detail.add(getFormattedPrice());
        ticket_detail.add(getStringTime());
        ticket_detail.add(getDirection());
        return ticket_detail;
    }
}
