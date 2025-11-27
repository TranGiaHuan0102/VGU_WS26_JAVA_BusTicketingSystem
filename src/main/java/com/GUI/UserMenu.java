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
import com.exceptions.TicketDeletionException;

public class UserMenu extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserMenu.class.getName());
    private final DatabaseController dbc;
    private final String id;
    private final String username;
    /**
     * Creates new form Menu
     */

    public UserMenu(DatabaseController dbc, String id, String username){
        initComponents();
        this.dbc = dbc;
        this.id = id;
        this.username = username;
        jLabel2.setText("Welcome, " + this.username + "!");
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
        jLabel2 = new javax.swing.JLabel();
        SeeTicketsButton = new javax.swing.JButton();
        BuyTicketButton = new javax.swing.JButton();
        SignOutButton = new javax.swing.JButton();
        DeleteExpiredTickets = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 5, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Welcome, ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
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

        DeleteExpiredTickets.setText("Delete Expired Tickets");
        DeleteExpiredTickets.setToolTipText("");
        DeleteExpiredTickets.setMaximumSize(new java.awt.Dimension(90, 25));
        DeleteExpiredTickets.setMinimumSize(new java.awt.Dimension(90, 25));
        DeleteExpiredTickets.addActionListener(this::DeleteExpiredTicketsActionPerformed);

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
                .addContainerGap()
                .addComponent(DeleteExpiredTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignOutButton)
                    .addComponent(DeleteExpiredTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
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
        String[] column_names = {"Student ID", "Type", "Active Date", "Expiry Date", "Price", "Morning Pick-up", "Afternoon Pick-up", "Expiry Status"};
        
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
        String[] column_names = {"Student ID", "Type", "Departure Date", "Price", "Pick-up Time", "Direction"};
        
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
        BusBooking BB = new BusBooking(dbc, id, username);
        BB.setVisible(true);
        BB.setLocationRelativeTo(null);
    }//GEN-LAST:event_BuyTicketButtonActionPerformed

    // Sign Out Button
    private void SignOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignOutButtonActionPerformed
        this.dispose();
        UserLogin ul = new UserLogin(dbc);
        ul.setVisible(true);
        ul.setLocationRelativeTo(null);
    }//GEN-LAST:event_SignOutButtonActionPerformed

    // Delete Expired Tickets Buttion
    private void DeleteExpiredTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteExpiredTicketsActionPerformed
        try{
            dbc.delete_expired(id);
        }
        catch (TicketDeletionException TDe){
            System.out.println("Error deleting tickets: " + TDe.getMessage());
        }
    }//GEN-LAST:event_DeleteExpiredTicketsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuyTicketButton;
    private javax.swing.JButton DeleteExpiredTickets;
    private javax.swing.JButton SeeTicketsButton;
    private javax.swing.JButton SignOutButton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
