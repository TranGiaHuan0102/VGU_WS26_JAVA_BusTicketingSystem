package package1.locations;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class LocationRepository{
    private static Map<String, Location> locations = new HashMap<>();
    
    static{
        locations.put("Turtle Lake", 
            new Location("Turtle Lake", 45, 85, LocalTime.of(6, 0), LocalTime.of(6, 45)));
        locations.put("Hang Xanh",
            new Location("Hang Xanh", 67, 80, LocalTime.of(6, 10), LocalTime.of(6, 55)));
        locations.put("Binh Trieu", 
            new Location("Binh Trieu", 38, 71, LocalTime.of(6, 25), LocalTime.of(7, 05)));
        locations.put("Binh Phuoc Crossroads",
            new Location("Binh Phuoc Crossroads", 36, 67, LocalTime.of(6, 50), LocalTime.of(7, 15)));
        locations.put("Binh Duong Aeon Mall",
            new Location("Binh Duong Aeon Mall", 25, 47, LocalTime.of(7, 05), LocalTime.of(7, 35)));
        locations.put("Becamex Tower",
            new Location("Becamex Tower", 19, 36, LocalTime.of(7, 20), LocalTime.of(7, 50)));
    }
    
    public static Location getLocation(String name){
        return locations.get(name);
    }
    
    public static boolean isValidLocation(String name) {
        return locations.containsKey(name);
    }
}
