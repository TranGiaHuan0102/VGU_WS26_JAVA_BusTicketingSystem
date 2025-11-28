/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.GUI;
/**
 *
 * @author caoda
 */
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.lang.StringBuilder;
import java.util.Map;
import java.util.List;

import com.controller.database.DatabaseController;
import com.controller.java.ticketdetails.TicketDetails;
import com.exceptions.TicketSelectionException;

public class UserMenu extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserMenu.class.getName());
    private final DatabaseController dbc;
    private final String user_type;
    private final String id;
    private final String username;
    /**
     * Creates new form Menu
     */

    public UserMenu(DatabaseController dbc, String user_type, String id, String username){
        initComponents();
        // ADD DB CONTROLLER
        this.dbc = dbc;
        
        // SET USER INFO
        this.user_type = user_type;
        this.id = id;
        this.username = username;
        
        // UPDATE TITLE CARD BASED ON USER TYPE
        if (user_type.equals("Student")){
            UserMenuWelcomeMessage.setText("What's good, " + this.username + "?");
        }
        else{
            UserMenuWelcomeMessage.setText("Warmest salutations, Prof. " + this.username + "!");
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

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        UserMenuWelcomeMessage = new javax.swing.JLabel();
        SeeTicketsButton = new javax.swing.JButton();
        BuyTicketButton = new javax.swing.JButton();
        SignOutButton = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 5, true));

        UserMenuWelcomeMessage.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        UserMenuWelcomeMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UserMenuWelcomeMessage.setText("W");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(UserMenuWelcomeMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(UserMenuWelcomeMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        SeeTicketsButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SeeTicketsButton.setText("See Tickets");
        SeeTicketsButton.addActionListener(this::SeeTicketsButtonActionPerformed);

        BuyTicketButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BuyTicketButton.setText("Buy Tickets");
        BuyTicketButton.addActionListener(this::BuyTicketButtonActionPerformed);

        SignOutButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SignOutButton.setText("Sign out");
        SignOutButton.addActionListener(this::SignOutButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BuyTicketButton, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(SeeTicketsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SignOutButton)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(BuyTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(SeeTicketsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(SignOutButton)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // My Ticket Button
    private void SeeTicketsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeeTicketsButtonActionPerformed
        try{
            // List containing ticket details of both ticket types (long-term and oneway)
            Map<String, List> ticket_details = dbc.search(id);
            
            // Separate them 
            List<TicketDetails> longterm_details = ticket_details.get("LONGTERM");
            List<TicketDetails> oneway_details = ticket_details.get("ONEWAY");
            
            // Null check
            if (longterm_details.isEmpty() && oneway_details.isEmpty()){
                JOptionPane.showMessageDialog(this, "No tickets found!", username + "'s Ticket", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Create dialog
            JDialog ticketDialog = new JDialog(this, username + "'s Ticket", true);
            ticketDialog.setSize(1000, 1200);
            ticketDialog.setLocationRelativeTo(this);

            // Create panel to hold both tables
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            
            // Long-term tickets table
            if  (!longterm_details.isEmpty()){
                JLabel longtermLabel = new JLabel("Long-Term Tickets");
                longtermLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
                
                JTable longtermTable = createLongTermTable(longterm_details);
                JScrollPane longtermScroll = new JScrollPane(longtermTable);
                
                mainPanel.add(longtermLabel);
                mainPanel.add(longtermScroll);
            }
            
            // One way tickets table
            if (!oneway_details.isEmpty()){
                JLabel onewayLabel = new JLabel("One Way Tickets");
                onewayLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
                
                JTable onewayTable = createOneWayTable(oneway_details);
                JScrollPane onewayScroll = new JScrollPane(onewayTable);
                onewayScroll.setPreferredSize(new Dimension(650, 200));
                
                mainPanel.add(onewayLabel);
                mainPanel.add(onewayScroll);
            }
            
            ticketDialog.add(mainPanel, BorderLayout.CENTER);
            ticketDialog.setVisible(true);
        }
        catch(TicketSelectionException TSe){
            JOptionPane.showMessageDialog(this, "Error finding tickets: " + TSe.getMessage());
        }
    }//GEN-LAST:event_SeeTicketsButtonActionPerformed

    // Helper to create LongTerm table
    private JTable createLongTermTable(List<TicketDetails> longterm_details){
        // Define column names
        String[] column_names = {"ID", "Type", "Price", "Bus Stop", "Morning Pick-up", "Afternoon Pick-up", "Active Date", "Expiry Date", "Expiry Status"};
        
        // Create data array
        Object[][] data = new Object[longterm_details.size()][column_names.length];
        
        // Fill data
        for (int i = 0; i < longterm_details.size(); i++){
            List ticket_detail = longterm_details.get(i).returnTicketDetail();
            
            // Convert TicketDetail to array row
            for (int j = 0; j < ticket_detail.size(); j++){
                data[i][j] = ticket_detail.get(j);
            }
        }
        
        JTable table = new JTable(data, column_names);
        table.setFillsViewportHeight(true);
        table.setEnabled(false); // Read-only table
        
        return table;
    }
    
    // Helper to create OneWay Table
    private JTable createOneWayTable(List<TicketDetails> oneway_details){
        // Define column names
        String[] column_names = {"ID", "Type", "Price", "Direction", "Departure Date", "Departure Time"};
        
        // Create data array
        Object[][] data = new Object[oneway_details.size()][column_names.length];
        
        // Fill data
        for (int i = 0; i < oneway_details.size(); i++){
            List ticket_detail = oneway_details.get(i).returnTicketDetail();
            
            // Convert TicketDetail to array row
            for (int j = 0; j < ticket_detail.size(); j++){
                data[i][j] = ticket_detail.get(j);
            }
        }
        
        JTable table = new JTable(data, column_names);
        table.setFillsViewportHeight(true);
        table.setEnabled(false); // Read-only table
        return table;
    }
    
    // Buy Ticket Button
    private void BuyTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyTicketButtonActionPerformed
        this.dispose();
        BusBooking BB = new BusBooking(dbc, user_type, id, username);
        BB.setVisible(true);
        BB.setLocationRelativeTo(null);
    }//GEN-LAST:event_BuyTicketButtonActionPerformed

    // Sign Out Button
    private void SignOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutButtonActionPerformed
        this.dispose();
        UserLogin ul = new UserLogin(dbc, user_type);
        ul.setVisible(true);
        ul.setLocationRelativeTo(null);
    }//GEN-LAST:event_SignOutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuyTicketButton;
    private javax.swing.JButton SeeTicketsButton;
    private javax.swing.JButton SignOutButton;
    private javax.swing.JLabel UserMenuWelcomeMessage;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
