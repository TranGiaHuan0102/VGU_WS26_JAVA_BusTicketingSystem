package com.java.ticketdetails;

/**
 *
 * @author tokuden
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.lang.StringBuilder;

public class WeeklyDailyDetails extends TicketDetails{
    private final LocalDate expiry_date;
    private final String type;
    private final LocalTime morning_pickuptime;
    private final LocalTime afternoon_pickuptime;
    
    public WeeklyDailyDetails(String id, LocalDate start_date, String location, long price, LocalDate end_date, String type, LocalTime morning_pickuptime, LocalTime afternoon_pickuptime){
        super(id, start_date, location, price);
        this.expiry_date = end_date;
        this.type = type;
        this.morning_pickuptime = morning_pickuptime;
        this.afternoon_pickuptime = afternoon_pickuptime;
    }
    
    private int DaysUntilExpiry(){
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiry_date);
    }
    
    @Override
    public final String printTicket(){
        StringBuilder ticket_detail = new StringBuilder();
        
        ticket_detail.append("Student ID: ").append(getID()).append("\n");
        ticket_detail.append("Type: ").append(this.type).append("\n");
        ticket_detail.append("Price: ").append(getFormattedPrice()).append("\n");
        
        ticket_detail.append("Journey: VGU - ").append(getLocation()).append("\n");
        
        String active_period = "Active: " + getStartDate().toString() + " to " + this.expiry_date.toString();
        ticket_detail.append(active_period).append("\n");
        
        String morning = "Morning: " + this.morning_pickuptime.toString() + " at " + getLocation();
        String afternoon = "Afternoon: " + this.afternoon_pickuptime.toString() + " at VGU";
        ticket_detail.append("Pickup Schedule:\n").append(morning).append("\n").append(afternoon).append("\n");
        
        if (DaysUntilExpiry() <= 0){
            ticket_detail.append("Ticket EXPIRED! Please purchase a new ticket to continue using service!").append("\n");
        }
        else{
            ticket_detail.append("Ticket ACTIVE! ").append(Integer.toString(DaysUntilExpiry())).append(" days until expiry!").append("\n");
        }
        
        return ticket_detail.toString();
    }
}
