/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerBarberViewUpApps;
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
 * this class is used to represent the screen which display upcoming appointments for the user
 * @author Fernando
 */
public class BarberViewUpAppsScreen extends JFrame {
    
    private JTable appointments;
    private ControllerBarberViewUpApps controller;
    // columns for the table
    private final String[] columnNames = {"id", "Customer Name", "Phone Number", "Email", "Status", "Date and Time"};
    private TableModel tableModel;
    
    public BarberViewUpAppsScreen(ControllerBarberViewUpApps controller, Object[][] data) {
        
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
        JLabel l1 = new JLabel("Upcoming Appointments");
        p1.add(l1);
        
        
        // add center                
        // Initializing the JTable         
        tableModel = new TableModel(data, columnNames);;
        
        appointments = new JTable(tableModel);         
        JScrollPane sp = new JScrollPane(appointments);
        appointments.setSelectionModel(new ForcedListSelectionModel());

        
        // add Buttons
        JPanel p2 = new JPanel();
        FlowLayout pageEndLayout = new FlowLayout(); 
        pageEndLayout.setAlignment(FlowLayout.CENTER);
        p2.setLayout(pageEndLayout); 
        JButton canceled = new JButton("Reject Appointment");
        canceled.setActionCommand("rejected");
        canceled.addActionListener(controller);
        JButton confirmed = new JButton("Confirm Appointment");
        confirmed.setActionCommand("confirmed");
        confirmed.addActionListener(controller);        
        JButton arrived = new JButton("Client arrived");
        arrived.setActionCommand("arrived");
        arrived.addActionListener(controller);  
        JButton completed = new JButton("Finish appointment");
        completed.setActionCommand("completed");
        completed.addActionListener(controller);  
        JButton refresh = new JButton("Refresh Table");
        refresh.setActionCommand("refresh");
        refresh.addActionListener(controller);  
        
        p2.add(canceled);
        p2.add(confirmed);
        p2.add(arrived);
        p2.add(completed);
        p2.add(refresh);
        
        this.add(p1, BorderLayout.PAGE_START);
        this.add(sp, BorderLayout.CENTER);
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
    
    // set status on the screen (status column)
    public void setStatus(String status) {
        
        tableModel.setValueAt(status, appointments.getSelectedRow(), 4);
        
    }
    
    // get app selected on the table
    public String[] getAppSelected() {
        
        
        if (appointments.getSelectedRow() >= 0) {
            
            String[] app = new String[6];
            app[0] = appointments.getValueAt(appointments.getSelectedRow(), 0).toString();
            app[1] = appointments.getValueAt(appointments.getSelectedRow(), 1).toString();
            app[2] = appointments.getValueAt(appointments.getSelectedRow(), 2).toString();
            app[3] = appointments.getValueAt(appointments.getSelectedRow(), 3).toString();
            app[4] = appointments.getValueAt(appointments.getSelectedRow(), 4).toString();
            app[5] = appointments.getValueAt(appointments.getSelectedRow(), 5).toString();
            return app;
        }
        return null;           
       
    }
    
}


