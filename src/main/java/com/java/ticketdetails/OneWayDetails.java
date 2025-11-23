package com.java.ticketdetails;

/**
 *
 * @author tokuden
 */

import java.time.LocalDate;
import java.time.LocalTime;

public class OneWayDetails extends TicketDetails{
    private final String type = "ONE WAY";
    private final String direction;
    private final LocalTime pickuptime;
    
    public OneWayDetails(String id, LocalDate start_date, String location, long price, String direction, LocalTime pickuptime){
        super(id, start_date, location, price);
        this.direction = direction;
        this.pickuptime = pickuptime;
    }
    
    @Override
    public final String printTicket(){
        StringBuilder ticket_detail = new StringBuilder();
        
        ticket_detail.append("Student ID: ").append(getID()).append("\n");
        
        ticket_detail.append("Type: ").append(this.type).append("\n");
        ticket_detail.append("Price: ").append(getFormattedPrice()).append("\n");
        
        String journey_message = (direction.equals("F")) ? ("From VGU to " + getLocation()) : ("From " + getLocation() + " to VGU");
        ticket_detail.append(journey_message).append("\n");
        
        String pickup = "Pick up on " + getStartDate().toString() + " at " + this.pickuptime.toString()  + " (" + getLocation() + ")";
        ticket_detail.append(pickup).append("\n");
        
        return ticket_detail.toString();
    }
}
