package com.exceptions;

/**
 *
 * @author tokuden
 */
public class TicketInsertionException extends Exception {
    public TicketInsertionException(){
        super("Unable to process this transaction!");
    }
    
    public TicketInsertionException(String message){
        super(message);
    }
}
