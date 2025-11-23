/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ui;
/**
 *
 * @author caoda
 */
import java.util.List;

import com.controller.DatabaseController;
import com.exceptions.*;
import com.java.ticketdetails.TicketDetails;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Insets;

import java.lang.StringBuilder;

public class Menu extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Menu.class.getName());
    private final DatabaseController dbc;
    private final String id;
    /**
     * Creates new form Menu
     */

    public Menu(DatabaseController dbc, String id){
        initComponents();
        this.dbc = dbc;
        this.id = id;
        jLabel2.setText("Welcome!" + " " + id);
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
        MyTicketButton = new javax.swing.JButton();
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
        jLabel2.setText("Welcome!");

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

        MyTicketButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MyTicketButton.setText("My Ticket");
        MyTicketButton.addActionListener(this::MyTicketButtonActionPerformed);

        BuyTicketButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BuyTicketButton.setText("Buy Ticket");
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
                    .addComponent(MyTicketButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(MyTicketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignOutButton)
                    .addComponent(DeleteExpiredTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Helper function of MyTicket
    private String AllTicketsString(List<TicketDetails> ticket_details){
        // Build string for all tickets
        StringBuilder allTickets = new StringBuilder();
        
        for (int i = 0; i < ticket_details.size(); i++){
            allTickets.append("==========TICKET #").append(i + 1).append("===============").append("\n");
            allTickets.append(ticket_details.get(i).printTicket());

            if (i < ticket_details.size() - 1){
                allTickets.append("=================================\n\n");
            }
        }
        
        return allTickets.toString();
    }
    
    // My Ticket Button
    private void MyTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyTicketButtonActionPerformed
        try{
            List<TicketDetails> ticket_details = dbc.search(id);
            
            if (ticket_details.isEmpty()){
                JOptionPane.showMessageDialog(this, "No tickets found!", "My Tickets", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Create a simple dialog
            JDialog ticketDialog = new JDialog(this, "My Ticket", true);
            ticketDialog.setSize(500, 600);
            ticketDialog.setLocationRelativeTo(this);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setMargin(new Insets(10, 10, 10, 10));
            
            // Show all tickets
            textArea.setText(AllTicketsString(ticket_details));
            
            // Add to dialog with scroll pane
            JScrollPane scrollPane = new JScrollPane(textArea);
            ticketDialog.add(scrollPane);
            
            ticketDialog.setVisible(true);
        }
        catch(TicketSelectionException TSe){
            JOptionPane.showMessageDialog(this, "Error finding tickets: " + TSe.getMessage());
        }
    }//GEN-LAST:event_MyTicketButtonActionPerformed

    // Buy Ticket Button
    private void BuyTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyTicketButtonActionPerformed
        this.dispose();
        BusBooking BB = new BusBooking(dbc, id);
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
    private javax.swing.JButton MyTicketButton;
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
