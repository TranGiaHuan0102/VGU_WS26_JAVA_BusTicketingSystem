package com.database.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CRUD_Helpers {
    protected static boolean eligible(Connection conn, String id){
        // Can only re-register if current daily/weekly ticket expires
        boolean eligible = true;

        String selectStmt = "SELECT end_date FROM weekly_daily WHERE id = ? ORDER BY end_date DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(selectStmt)){
            stmt.setString(1, id);

            ResultSet rSet = stmt.executeQuery();
            if (rSet.next()){
                LocalDate endDate = rSet.getDate("end_date").toLocalDate();
                if (endDate.isAfter(LocalDate.now())){
                    eligible = false;
                }
            }

        }
        catch(SQLException sqle){
            System.out.println("Check failed: " + sqle.getMessage());
            sqle.printStackTrace();
            eligible = false; // default to not eligible on error
            }
        return eligible;
    }
}