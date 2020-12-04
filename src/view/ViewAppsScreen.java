/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerViewApps;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This class is used to draw the screen with the appointments for a customer
 * @author Fernando
 */
public class ViewAppsScreen extends JFrame {
    
    private JButton cancel;
    private JButton feedback;
    private JTable appointments;
    private ControllerViewApps controller;
    // columns for the table 
    private final String[] columnNames = {"id", "Barber Name", "Phone Number", "Email", "Location", "Status", "Date and Time"};
    private TableModel tableModel;
    
    public ViewAppsScreen(ControllerViewApps controller, Object[][] data) {
        
        this.controller = controller;
        
        // We encapsulated the building process of the window
        attributesSetter();
        components(data);
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(900,550);
        this.setTitle("Barber Application");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    private void components(Object[][] data) {
        
        
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
                
        // title
        JPanel p1 = new JPanel();
        FlowLayout titleLayout = new FlowLayout(); 
        titleLayout.setAlignment(FlowLayout.CENTER);
        p1.setLayout(titleLayout); 
        JLabel l1 = new JLabel("My Appointments");
        p1.add(l1);
        
        
        // add center                
        // Initializing the JTable         
        tableModel = new TableModel(data, columnNames);;
        
        appointments = new JTable(tableModel);         
        JScrollPane sp = new JScrollPane(appointments);
        appointments.setSelectionModel(new ForcedListSelectionModel());

        
        // add Buttons to the screen
        JPanel p2 = new JPanel();
        FlowLayout pageEndLayout = new FlowLayout(); 
        pageEndLayout.setAlignment(FlowLayout.CENTER);
        p2.setLayout(pageEndLayout); 
        cancel = new JButton("Cancel Appointment");
        cancel.setActionCommand("cancelled");
        cancel.addActionListener(controller);
        feedback = new JButton("See Feedback");
        feedback.setActionCommand("feedback");
        feedback.addActionListener(controller);        
        p2.add(cancel);
        p2.add(feedback);
        
        // title
        this.add(p1, BorderLayout.PAGE_START);
        // table
        this.add(sp, BorderLayout.CENTER);
        // buttons
        this.add(p2, BorderLayout.PAGE_END);
                
    }
    

    private void validation() {
        this.validate();
        this.repaint();
    }
        
    public void showInformationMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
        // Show message on the screen with a popup
    public void showErrorMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.ERROR_MESSAGE);
         
    }
    
    // set status for a row
    public void setStatus(String status) {
        
        tableModel.setValueAt(status, appointments.getSelectedRow(), 5);
        
    }
    
    // get appointment selected
    public String[] getAppSelected() {
        
        
        if (appointments.getSelectedRow() >= 0) {
            
            String[] app = new String[7];
            app[0] = appointments.getValueAt(appointments.getSelectedRow(), 0).toString();
            app[1] = appointments.getValueAt(appointments.getSelectedRow(), 1).toString();
            app[2] = appointments.getValueAt(appointments.getSelectedRow(), 2).toString();
            app[3] = appointments.getValueAt(appointments.getSelectedRow(), 3).toString();
            app[4] = appointments.getValueAt(appointments.getSelectedRow(), 4).toString();
            app[5] = appointments.getValueAt(appointments.getSelectedRow(), 5).toString();
            app[6] = appointments.getValueAt(appointments.getSelectedRow(), 6).toString();
            return app;
        }
        return null;           
       
    }
    
}


