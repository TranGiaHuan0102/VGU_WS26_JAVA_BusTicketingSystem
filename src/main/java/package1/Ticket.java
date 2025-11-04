package package1;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.text.ParsePosition;
import java.text.NumberFormat;
import package1.locations.LocationRepository;


public abstract class Ticket implements Validatable {
    private String student_id;
    private String ticket_type;
    private String location;
    private LocalDate start_date;
    
    // Constructor
    Ticket(String student_id, String ticket_type, String location, LocalDate date){
        this.student_id = student_id;
        this.ticket_type = ticket_type;
        this.location = location;
        this.start_date = date;
    }
    
    // Methods to verify the validity of input
    public static String loc_verified(String location){
        if (!LocationRepository.isValidLocation(location)){
            throw new IllegalArgumentException("Invalid Location!");
        }
        return location;
    }
    
    public static boolean isNumeric(String str){
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }
    
    public static String id_verified(String id){
        if (id.length() != 8 || !isNumeric(id)){
            throw new IllegalArgumentException("Invalid ID!");
        }
        return id;
    }
    
    public static LocalDate date_verified(String date_string){
        try{
            return LocalDate.parse(date_string);
        }
        catch(DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format!");
        }   
    }
    
    // Getters:
    LocalDate getDate(){return this.start_date;}
    
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
    
    void generic_ticket_info(){
        System.out.println("Ticket Holder ID: " + student_id);
        System.out.println("Type: " + ticket_type);
        System.out.println("Journey: " + location + " - VGU");
    }
    
    abstract void specific_ticket_info();
    
    // Interface methods - force subclasses to implement
    @Override
    public abstract boolean isValid();
    
    @Override
    public abstract boolean isExpired();
    
    @Override
    public abstract int remaining();
}