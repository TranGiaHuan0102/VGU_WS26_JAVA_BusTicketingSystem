package com.java.tickets;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static com.java.tickets.Ticket.date_verified;


public class WeeklyTicket extends Ticket implements Validatable{
    private final LocalDate end_date;
    
    // Constructor
    WeeklyTicket(String id, String start_date, String location_name){
        super(id, date_verified(start_date), location_name);
        this.end_date = getStartDate().plusDays(180);
    }
    
    // Class method
    public static WeeklyTicket create(String id, String start_date, String location_name){
        WeeklyTicket wt = new WeeklyTicket(id, start_date, location_name);
        return wt;
    }
    
    
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
    
    // Getters
    public LocalDate getEndDate(){return this.end_date;}
}

