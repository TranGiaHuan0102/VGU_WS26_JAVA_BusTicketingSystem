package com.database;

public class ConfigLoader implements ConfigInterface {

    // Constructor
    public ConfigLoader() {}
    
    // Getters
    public String getUrl() { return url; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
