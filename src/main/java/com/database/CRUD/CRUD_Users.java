
package com.database.CRUD;

/**
 *
 * @author tokuden
 */
import com.controller.java.users.Student;
import com.controller.java.users.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import com.exceptions.*;

public class CRUD_Users {
    public static void insert_user(Connection conn, User u) throws NewUserException{
        if (u instanceof Student){
            insert_user(conn, (Student) u);
        }
    }
    
    private static void insert_user(Connection conn, Student s) throws NewUserException{
        String insert_Stmt = "INSERT INTO public.user (id, first_name, last_name, password, email, user_type) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(insert_Stmt)){
            stmt.setString(1, s.getStudentID());
            stmt.setString(2, s.getFirstName());
            stmt.setString(3, s.getLastName());
            stmt.setString(4, s.getPassword());
            stmt.setString(5, s.getEmailAddress());
            stmt.setString(6, "STUDENT");
            
            stmt.executeUpdate();
        }
        catch (SQLException sqle){
            throw new NewUserException(sqle.getMessage());
        }
    }
    
    public static User search_user(Connection conn, String id) throws LogInException, UserSelectionException{
    String select_Stmt = "SELECT * FROM public.user WHERE (id = ?)";
    try(PreparedStatement stmt = conn.prepareStatement(select_Stmt)){
        stmt.setString(1, id);

        // rSet should only have at most 1 element 
        ResultSet rSet = stmt.executeQuery();

        // Return User obj from rSet's data
        
        if (rSet.next()){
            if (rSet.getString("user_type").equals("STUDENT")){
                String first_name = rSet.getString("first_name");
                String last_name = rSet.getString("last_name");
                String email = rSet.getString("email");
                String password = rSet.getString("password");
                return (User) new Student(first_name, last_name, email, password, id);
            }
        }
        else{
            // No results found
            throw new LogInException("User does not exist!");
        }    
        
    }
    catch(SQLException sqle){
        throw new UserSelectionException(sqle.getMessage());
    }
    
    throw new UserSelectionException("User type not supported!");
    }
}
