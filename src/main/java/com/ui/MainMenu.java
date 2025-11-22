package com.ui;
/**
 *
 * @author caoda
 */
import com.controller.DatabaseController;
import com.exceptions.DatabaseConnectionException;

public class MainMenu extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainMenu.class.getName());
    private DatabaseController dbc;
    /**
     * Creates new form MainMenu
     */
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
        
        // CLOSE BUTTON
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent windowEvent){
//                // Close database controller (if open)
//                if (dbc != null){
//                    dbc.close();
//                }
//                dispose();
//                System.exit(0);
//            }
//        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MainMenuLabel = new javax.swing.JLabel();
        StudentLoginButton = new javax.swing.JButton();

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

        MainMenuLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        MainMenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MainMenuLabel.setText("Main Menu");
        MainMenuLabel.setToolTipText("");

        StudentLoginButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        StudentLoginButton.setText("Student Login");
        StudentLoginButton.addActionListener(this::StudentLoginButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(282, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(StudentLoginButton)
                        .addGap(287, 287, 287))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(MainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(278, 278, 278))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(MainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(StudentLoginButton)
                .addGap(96, 96, 96))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Student Login button
    private void StudentLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentLoginButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        UserLogin ul = new UserLogin(dbc);
        ul.setVisible(true);
        ul.setLocationRelativeTo(null);
    }//GEN-LAST:event_StudentLoginButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MainMenuLabel;
    private javax.swing.JButton StudentLoginButton;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
