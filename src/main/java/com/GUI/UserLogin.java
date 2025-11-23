package com.GUI;
/**
 *
 * @author caoda
 */
import com.controller.database.DatabaseController;
import com.exceptions.LogInException;
import com.exceptions.UserSelectionException;

import com.controller.java.users.User;
public class UserLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserLogin.class.getName());
    private DatabaseController dbc;
    
    // Creates new form: User Login
    public UserLogin(DatabaseController dbc) {
        initComponents();
        
        // ADD DB CONTROLLER
        if (this.dbc == null){
            this.dbc = dbc;
        }
        
        // CLOSE BUTTON
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                // Close database controller (if open)
                if (dbc != null){
                    dbc.close();
                }
                dispose();
                System.exit(0);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        LogInButton = new javax.swing.JButton();
        SignUpButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        ErrorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log In as Student");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Student ID:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Password:");

        IDField.addActionListener(this::IDFieldActionPerformed);

        LogInButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LogInButton.setText("Login");
        LogInButton.addActionListener(this::LogInButtonActionPerformed);

        SignUpButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SignUpButton.setText("Sign up");
        SignUpButton.addActionListener(this::SignUpButtonActionPerformed);

        PasswordField.setToolTipText("");

        ErrorMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ErrorMessage.setForeground(new java.awt.Color(255, 51, 51));
        ErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LogInButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SignUpButton))
                            .addComponent(IDField, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(PasswordField))))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogInButton)
                    .addComponent(SignUpButton))
                .addGap(18, 18, 18)
                .addComponent(ErrorMessage)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("User Login");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDFieldActionPerformed
        
    }//GEN-LAST:event_IDFieldActionPerformed

    // Login Button
    private void LogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogInButtonActionPerformed
        String id = IDField.getText().trim();
        String password = new String(PasswordField.getPassword()).trim();
        
        // ID check
        if(id.isEmpty()){
            ErrorMessage.setText("Please input ID!");
            return;                
        }
        else if(!id.matches("\\d{8}")){
            ErrorMessage.setText("Invalid Student ID!");
            return;
        }
        
        //Password check
        if (password.isEmpty()){
            ErrorMessage.setText("Please input password!");
            return;
        }
        
        // Existence check
        String username;
        try{
            User u = dbc.search_user(id);
            if (!u.getPassword().equals(password)){
                ErrorMessage.setText("Wrong password!");
                return;
            }
            username = u.getFirstName();
        }
        catch (LogInException LIe){
            ErrorMessage.setText(LIe.getMessage());
            return;
        }
        catch (UserSelectionException USe){
            ErrorMessage.setText("Error: Unable to search database for user!");
            System.out.println(USe.getMessage());
            return;
        }
        
        dispose();
        UserMenu m = new UserMenu(dbc, id, username);
        m.setVisible(true);
        m.setLocationRelativeTo(null);
    }//GEN-LAST:event_LogInButtonActionPerformed

    // Sign-up Button
    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        NewUser nu = new NewUser(dbc);
        nu.setVisible(true);
        nu.setLocationRelativeTo(null); 
    }//GEN-LAST:event_SignUpButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorMessage;
    private javax.swing.JTextField IDField;
    private javax.swing.JButton LogInButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
