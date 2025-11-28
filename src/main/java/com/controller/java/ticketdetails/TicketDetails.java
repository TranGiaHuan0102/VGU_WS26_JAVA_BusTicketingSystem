package com.controller.java.ticketdetails;

/**
 *
 * @author tokuden
 */
import java.time.LocalDate;
import java.util.List;

public abstract class TicketDetails {
    private final String id;
    private final LocalDate start_date;
    private final String location;
    private final long price;
    private final String type;
    
    // Default constructor
    protected TicketDetails(String id, LocalDate start_date, String location, long price, String type){
        this.id = id;
        this.start_date = start_date;
        this.location = location;
        this.price = price;
        this.type = type;
    }
    
    // Getters
    
    protected final String getID() {return this.id;}
    protected final LocalDate getStartDate() {return this.start_date;}
    protected final String getStringStartDate() {return this.start_date.toString();}
    protected final String getLocation() {return this.location;}
    protected String getFormattedPrice(){return String.format("%,d VND", this.price);}  /*Helper method for price formatting*/
    protected String getType(){return this.type;}
    
    // Print ticket
    public abstract List<String> returnTicketDetail();
    
    
}
