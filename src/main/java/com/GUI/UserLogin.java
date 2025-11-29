package com.GUI;
/**
 *
 * @author caoda
 */
import java.awt.event.KeyEvent;

import com.controller.database.DatabaseController;
import com.exceptions.LogInException;
import com.exceptions.UserSelectionException;

import com.controller.java.users.User;
public class UserLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserLogin.class.getName());
    private DatabaseController dbc;
    private final String user_type;
    
    // Creates new form: User Login
    public UserLogin(DatabaseController dbc, String user_type) {
        initComponents();
        
        // ADD DB CONTROLLER
        if (this.dbc == null){
            this.dbc = dbc;
        }
        
        // UPDATE USER TYPE
        this.user_type = user_type;
        
        // UPDATE TITLE CARD AND ID TEXT
        LogInTitle.setText("Log In as " + this.user_type);
        IDText.setText(this.user_type + " ID:");
        
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
        
        // LOGIN VIA ENTER ANYWHERE
        getRootPane().setDefaultButton(BackButton);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogInTitle = new javax.swing.JLabel();
        IDText = new javax.swing.JLabel();
        PasswordText = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        LogInButton = new javax.swing.JButton();
        SignUpButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        ErrorMessage = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        LogInTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LogInTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LogInTitle.setText("Log In As");
        LogInTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        IDText.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        IDText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDText.setText("User ID:");

        PasswordText.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PasswordText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PasswordText.setText("Password:");

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

        BackButton.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(this::BackButtonActionPerformed);
        BackButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BackButtonEscapeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BackButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 190, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IDText, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LogInButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SignUpButton))
                            .addComponent(PasswordField)
                            .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(199, 199, 199))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LogInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BackButton)
                .addGap(59, 59, 59)
                .addComponent(LogInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogInButton)
                    .addComponent(SignUpButton))
                .addGap(18, 18, 18)
                .addComponent(ErrorMessage)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        LogInTitle.getAccessibleContext().setAccessibleName("User Login");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            ErrorMessage.setText("Invalid ID!");
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
            if (!u.getPassword().trim().equals(password.trim())){
                System.out.println(password);
                System.out.println(u.getPassword());
                ErrorMessage.setText("Wrong password!");
                return;
            }
            
            if (!user_type.toUpperCase().equals(u.getStringUserType())){
                ErrorMessage.setText("Conflicting user type!");
                return;
            }
            username = u.getFirstName();
        }
        catch (LogInException LIe){
            ErrorMessage.setText(LIe.getMessage());
            System.out.println(LIe.getMessage());
            return;
        }
        catch (UserSelectionException USe){
            ErrorMessage.setText("Error: Unable to search database for user!");
            System.out.println(USe.getMessage());
            return;
        }
        
        this.dispose();
        UserMenu m = new UserMenu(dbc, user_type, id, username);
        m.setVisible(true);
        m.setLocationRelativeTo(null);
    }//GEN-LAST:event_LogInButtonActionPerformed

    // Sign-up Button
    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        this.dispose();
        NewUser nu = new NewUser(dbc, user_type);
        nu.setVisible(true);
        nu.setLocationRelativeTo(null); 
    }//GEN-LAST:event_SignUpButtonActionPerformed

    // Back button shortcut
    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.dispose();
        MainMenu mm = new MainMenu(dbc);
        mm.setVisible(true);
        mm.setLocationRelativeTo(null);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void BackButtonEscapeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BackButtonEscapeKeyPressed
        // Also Back upon Esc
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            BackButton.doClick();
        }
    }//GEN-LAST:event_BackButtonEscapeKeyPressed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel ErrorMessage;
    private javax.swing.JTextField IDField;
    private javax.swing.JLabel IDText;
    private javax.swing.JButton LogInButton;
    private javax.swing.JLabel LogInTitle;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordText;
    private javax.swing.JButton SignUpButton;
    // End of variables declaration//GEN-END:variables
}
