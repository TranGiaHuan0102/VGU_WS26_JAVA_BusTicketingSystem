package com.tokuden.database;

import com.database.startup.load_locations;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUD {
    public static void createOperation(Connection conn, String SQLFile) throws SQLException, IOException {
        InputStream sqlStream = CRUD.class.getClassLoader().getResourceAsStream(SQLFile);
        
        if (sqlStream == null) {
        throw new IOException(SQLFile + " not found!");
        }
    
        // Read file content
        String createLocationsStatement = new String(sqlStream.readAllBytes());

        // Execute
        var statement = conn.createStatement();
        
        statement.execute(createLocationsStatement);
        System.out.println("TABLE FROM " + SQLFile +" CREATED!");
    }
    
    public static void updateOperation(Connection conn, String statement, String CSVFile) throws SQLException, IOException{
        InputStream csvStream = load_locations.class.getClassLoader().getResourceAsStream(CSVFile);
        
        if (csvStream == null){
            throw new IOException(CSVFile + " not found");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
        String line;
        reader.readLine();  // Skip header
        
        PreparedStatement stmt = conn.prepareStatement(statement);
        while ((line = reader.readLine()) != null){
            String[] array = line.split(",");
            
            for (int i = 0; i < array.length; i++){
                String value = array[i].trim();

                if (value.isEmpty() || value.equalsIgnoreCase("null")) {
                    stmt.setNull(i + 1, java.sql.Types.NULL);
                } else if (isInteger(value)) {
                    stmt.setInt(i + 1, Integer.parseInt(value));
                } else if (isDouble(value)) {
                    stmt.setDouble(i + 1, Double.parseDouble(value));
                } else if (isTime(value)) {
                    stmt.setTime(i + 1, java.sql.Time.valueOf(value));
                } else if (isDate(value)) {
                    stmt.setDate(i + 1, java.sql.Date.valueOf(value));
                } else if (isDateTime(value)) {
                    stmt.setTimestamp(i + 1, java.sql.Timestamp.valueOf(value));
                } else {
                    stmt.setString(i + 1, value);
                }
            }
            
            stmt.executeUpdate();
        }
        
        System.out.println("TABLE UPDATED!");
    }
    
    // Helper functions for type detection
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            // exclude integer-like doubles (e.g., "5.0")
            return s.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isDate(String s) {
        // Matches yyyy-MM-dd
        return s.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    private static boolean isTime(String s) {
        // Matches HH:mm:ss
        return s.matches("\\d{2}:\\d{2}:\\d{2}");
    }

    private static boolean isDateTime(String s) {
        // Matches yyyy-MM-dd HH:mm:ss
        return s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }
}
