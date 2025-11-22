package com.java.ticketdetails;

/**
 *
 * @author tokuden
 */
import java.time.LocalDate;

public abstract class TicketDetails {
    private final String id;
    private final LocalDate start_date;
    private final String location;
    private final long price;
    
    
    // Default constructor
    TicketDetails(String id, LocalDate start_date, String location, long price){
        this.id = id;
        this.start_date = start_date;
        this.location = location;
        this.price = price;
    }
    
    // Print ticket
    // abstract void printTicket();
}
