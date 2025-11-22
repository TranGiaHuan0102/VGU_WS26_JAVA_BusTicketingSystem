package com.java.tickets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class DailyTicket extends Ticket implements Validatable{
    private final LocalDate end_date;
    
    // Constructor
    DailyTicket(String id, LocalDate start_date, String location_name){ 
        super(id, start_date, location_name);
        this.end_date = getStartDate().plusDays(180);
        
    }
    
    public static DailyTicket create(String id, LocalDate start_date, String location_name){
        DailyTicket dt = new DailyTicket(id, start_date, location_name);
        return dt;
    }
    
    // Getters
    public LocalDate getEndDate(){return this.end_date;}
    
    // Interface implementations
    @Override
    public boolean isValid(){
        boolean valid = getStartDate().isBefore(this.end_date);
        super.ExpiryMessage(!valid);
        return valid;
    }
    
    @Override
    public boolean isExpired(){
        boolean expired = getStartDate().isAfter(this.end_date);
        super.ExpiryMessage(expired);
        return expired;
    }
    
    @Override
    public int remaining(){
        int remaining_days =  (int) ChronoUnit.DAYS.between(getStartDate(), this.end_date);
        super.RemainingDaysMessage(remaining_days);
        return remaining_days;
    }
}