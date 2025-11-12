package com.java.tickets;

public interface Validatable {
    boolean isValid();
    boolean isExpired();
    int remaining();
}
