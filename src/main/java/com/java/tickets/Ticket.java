package com.java.tickets;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public abstract class Ticket{
    private String id;
    private final LocalDate start_date;
    private final String location;
    
    
    // Constructor
    Ticket(String id,  LocalDate start_date, String location){
        this.id = id;
        this.start_date = start_date;
        this.location = location;
    }
    
    // Getters:
    public String getID(){return this.id;}
    public LocalDate getStartDate(){return this.start_date;}
    public String getLocation(){return this.location;}
    
    
    public void ExpiryMessage(boolean expired){
        if (expired){
            System.out.println("Expired ticket!");
        }
        else{
            System.out.println("Valid ticket!");
        }
    }
    
    public void RemainingDaysMessage(int days){
        if (days > 0){
            System.out.println("Ticket valid for " + days + " days!");
        }
        else{
            ExpiryMessage(true);
        }
    }
}