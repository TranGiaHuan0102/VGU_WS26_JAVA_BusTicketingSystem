package com.controller.java.ticketdetails;

/**
 *
 * @author tokuden
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.ArrayList;

public class LongTermDetails extends TicketDetails{
    private final LocalDate expiry_date;
    private final String type;
    private final LocalTime morning_pickuptime;
    private final LocalTime afternoon_pickuptime;
    
    public LongTermDetails(String id, LocalDate start_date, String location, long price, LocalDate end_date, String type, LocalTime morning_pickuptime, LocalTime afternoon_pickuptime){
        super(id, start_date, location, price);
        this.expiry_date = end_date;
        this.type = type;
        this.morning_pickuptime = morning_pickuptime;
        this.afternoon_pickuptime = afternoon_pickuptime;
    }
    
    private int DaysUntilExpiry(){
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiry_date);
    }
    
    // Getters
    protected String getType(){return this.type;}
    protected LocalDate getExpiryDate(){return this.expiry_date;}
    protected String getStringExpiryDate(){return this.expiry_date.toString();}
    protected LocalTime getMorningTime(){return this.morning_pickuptime;}
    protected String getStringMorningTime(){return this.morning_pickuptime.toString();}
    protected LocalTime getAfternoontime(){return this.afternoon_pickuptime;}
    protected String getStringAfternoonTime(){return this.afternoon_pickuptime.toString();}
    
    @Override
    public final List<String> returnTicketDetail(){
        List<String> ticket_detail = new ArrayList<>();
        ticket_detail.add(getID());
        ticket_detail.add(getType());
        ticket_detail.add(getStringStartDate());
        ticket_detail.add(getStringExpiryDate());
        ticket_detail.add(getFormattedPrice());
        ticket_detail.add(getStringMorningTime());
        ticket_detail.add(getStringAfternoonTime());
        
        String expiry_status = (DaysUntilExpiry() < 0) ? "EXPIRED" : "ACTIVE";
        ticket_detail.add(expiry_status);
        
        return ticket_detail;
    }
}
