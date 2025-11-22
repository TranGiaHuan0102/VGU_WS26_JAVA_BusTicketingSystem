package com.java.ticketdetails;

/**
 *
 * @author tokuden
 */
import java.time.LocalDate;
import java.time.LocalTime;

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
}
