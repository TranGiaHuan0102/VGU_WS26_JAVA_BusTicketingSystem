package com.GUI;
/**
 *
 * @author caoda
 */
import com.controller.database.DatabaseController;
import com.exceptions.DatabaseConnectionException;

public class MainMenu extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainMenu.class.getName());
    private DatabaseController dbc;
    /**
     * Creates new form MainMenu
     */
    
    // This gets called when app first boots up
    public MainMenu()  {
        initComponents();
        
        // ADD DB CONTROLLER
        try{
           if (dbc == null){
               this.dbc = new DatabaseController();
           } 
        }
        catch (DatabaseConnectionException DBe){
            DBe.printStackTrace();
            System.exit(0);
        }
        
        //CLOSE BUTTON
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

    // This gets called when the back button is hit
    public MainMenu(DatabaseController dbc){
        initComponents();
        
        // ADD DB CONTROLER
        this.dbc = dbc;
        
        // CLOSE BUTTON
        //CLOSE BUTTON
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

        jPanel1 = new javax.swing.JPanel();
        MainMenuLabel = new javax.swing.JLabel();
        StudentLoginButton = new javax.swing.JButton();
        ProfessorLogInButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainMenuLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        MainMenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MainMenuLabel.setText("BUS APP");
        MainMenuLabel.setToolTipText("");

        StudentLoginButton.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        StudentLoginButton.setText("Log In as Student");
        StudentLoginButton.addActionListener(this::StudentLoginButtonActionPerformed);

        ProfessorLogInButton.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        ProfessorLogInButton.setText("Log In as Professor");
        ProfessorLogInButton.addActionListener(this::ProfessorLogInButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(StudentLoginButton)
                .addGap(70, 70, 70)
                .addComponent(ProfessorLogInButton)
                .addGap(0, 119, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MainMenuLabel)
                .addGap(281, 281, 281))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(MainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StudentLoginButton)
                    .addComponent(ProfessorLogInButton))
                .addGap(126, 126, 126))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Student Login button
    private void StudentLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentLoginButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        UserLogin ul = new UserLogin(dbc, "Student");
        ul.setVisible(true);
        ul.setLocationRelativeTo(null);
    }//GEN-LAST:event_StudentLoginButtonActionPerformed

    private void ProfessorLogInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfessorLogInButtonActionPerformed
        this.dispose();
        UserLogin ul = new UserLogin(dbc, "Professor");
        ul.setVisible(true);
        ul.setLocationRelativeTo(null);
    }//GEN-LAST:event_ProfessorLogInButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MainMenuLabel;
    private javax.swing.JButton ProfessorLogInButton;
    private javax.swing.JButton StudentLoginButton;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
