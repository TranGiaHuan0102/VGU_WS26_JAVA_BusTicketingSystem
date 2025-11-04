package package1.locations;
import java.time.LocalTime;

public class Location{
    private final String name;
    private final long weekly_price;
    private final long daily_price;
    private final LocalTime weekly_pickup_time;
    private final LocalTime daily_pickup_time;

    
    // Constructor
    Location(String name, long weekly_price, long daily_price, 
             LocalTime weekly_pickup_time, LocalTime daily_pickup_time) {
        this.name = name;
        this.weekly_price = weekly_price * 100000;
        this.daily_price = daily_price * 100000;
        this.weekly_pickup_time = weekly_pickup_time;
        this.daily_pickup_time = daily_pickup_time;
    }
    
    // Getters
    public String getName() { return name; }
    public long getWeeklyPrice() { return weekly_price; }
    public long getDailyPrice() { return daily_price; }
    public LocalTime getWeeklyPickup() { return weekly_pickup_time; }
    public LocalTime getDailyPickup() { return daily_pickup_time; }
}


