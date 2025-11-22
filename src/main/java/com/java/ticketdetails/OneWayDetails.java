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
}
