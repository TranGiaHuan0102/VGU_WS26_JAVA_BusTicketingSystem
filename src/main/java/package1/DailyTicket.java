package package1;

import package1.locations.LocationRepository;
import package1.locations.Location;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static package1.Ticket.date_verified;
import static package1.Ticket.id_verified;
import static package1.Ticket.loc_verified;

class DailyTicket extends Ticket implements Validatable{
    private final LocalDate end_date;
    private final long price;
    private final LocalTime pickup_time;
    
    // Constructor
    DailyTicket(String id, String start_date, String location_name){ 
        super(id_verified(id), "DAILY", loc_verified(location_name), date_verified(start_date));
        this.end_date = getDate().plusDays(180);
        
        Location loc = LocationRepository.getLocation(location_name);
        this.price = loc.getDailyPrice();
        this.pickup_time = loc.getDailyPickup();
    }
    
    public static DailyTicket create(String id, String start_date, String location_name){
        DailyTicket dt = new DailyTicket(id, start_date, location_name);
        return dt;
    }
    
    @Override
    public void specific_ticket_info(){
        super.generic_ticket_info();
        System.out.println("Valid from " + getDate() + " to " + end_date);
        System.out.println("Bus Fee: " + price + " VND");
        System.out.println("Pickup: " + pickup_time + " (Mon to Fri)");
    }
    
    // Interface implementations
    @Override
    public boolean isValid(){
        boolean valid = getDate().isBefore(this.end_date);
        super.ExpiryMessage(!valid);
        return valid;
    }
    
    @Override
    public boolean isExpired(){
        boolean expired = getDate().isAfter(this.end_date);
        super.ExpiryMessage(expired);
        return expired;
    }
    
    @Override
    public int remaining(){
        int remaining_days =  (int) ChronoUnit.DAYS.between(getDate(), this.end_date);
        super.RemainingDaysMessage(remaining_days);
        return remaining_days;
    }
}