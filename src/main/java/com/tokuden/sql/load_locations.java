package com.tokuden.sql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class load_locations {
    
    
    public static void main(String args[]) {
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection succeded!");
            conn.close();
        }
        catch (SQLException e){
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
