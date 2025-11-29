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
    private final String direction;
    private final LocalTime pickuptime;
    
    public OneWayDetails(String id, LocalDate start_date, String location, long price, String type, String direction, LocalTime pickuptime){
        super(id, start_date, location, price, type);
        this.direction = direction;
        this.pickuptime = pickuptime;
    }
    
    // Getters
    protected String getStringTime(){return this.pickuptime.toString();}
    protected String getFormattedDirectionString(){
        if (this.direction.equals("TO")){
            return "FROM " + getLocation() + " TO VGU";
        }
        else{
            return "FROM VGU TO " + getLocation();
        }
    }
    
    @Override
    public final List<String> returnTicketDetail(){
        List<String> ticket_detail = new ArrayList<>();
        ticket_detail.add(getID());
        ticket_detail.add(getType());
        ticket_detail.add(getFormattedPrice());
        
        ticket_detail.add(getFormattedDirectionString());
        ticket_detail.add(getStringStartDate());
        ticket_detail.add(getStringTime());
        
        
        return ticket_detail;
    }
}
