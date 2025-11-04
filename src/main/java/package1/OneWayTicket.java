package package1;

import package1.locations.LocationRepository;
import package1.locations.Location;
import java.time.LocalDate;
import java.time.LocalTime;
import static package1.Ticket.date_verified;
import static package1.Ticket.id_verified;
import static package1.Ticket.loc_verified;

class OneWayTicket extends Ticket implements Validatable{
    private final LocalTime pickup_time;
    private final long price = 150000;
    
    public static char direction_verified(char direction){
        if (!(direction == 'T' || direction == 'F')){
            throw new IllegalArgumentException("Invalid Direction!");
        }
        return direction;
    }
    
    // Constructor
    OneWayTicket(String id, String date, String location_name, char direction){
        super(id_verified(id), "ONE WAY", loc_verified(location_name), date_verified(date));
        
        if (direction_verified(direction) == 'T'){
            Location loc = LocationRepository.getLocation(location_name);
            this.pickup_time = loc.getDailyPickup();
            
        }
        else{
            this.pickup_time = LocalTime.of(16, 30);
            
        }
    }
    
    // Class method
    public static OneWayTicket create(String id, String date, String location_name, char direction){
        OneWayTicket ot = new OneWayTicket(id, date, location_name, direction);
        return ot;
    }
    
    @Override
    void specific_ticket_info(){
        super.generic_ticket_info();
        System.out.println("Bus Fee: " + price + " VND");
        System.out.println("Pickup: " + pickup_time + ", " + getDate());
    }
    
    @Override
    public boolean isValid(){
        boolean valid =  getDate().equals(LocalDate.now());
        super.ExpiryMessage(!valid);
        return valid;
    }
    
    @Override
    public boolean isExpired(){
        boolean expired = !getDate().equals(LocalDate.now());
        super.ExpiryMessage(expired);
        return expired;
    }
    
    @Override
    public int remaining(){
        return 0;
    }
}
