/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerBarberHomeScreen;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
 

/**
 * This class is used to create the home screen for the barber
 * @author Fernando
 */
public class BarberHomeScreen extends JFrame {
    
    private String fullName;

    private ControllerBarberHomeScreen controller;
    
    public BarberHomeScreen(ControllerBarberHomeScreen controller, String fullName) {
        
        this.controller = controller;
        this.fullName = fullName;
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(300,250);
        this.setTitle("Home Screen");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(controller);

    }

    private void components() {
        
        // main layout
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar(); 
        JMenu menu = new JMenu("Menu");  
        JMenuItem itemLogout = new JMenuItem("Log out");
        itemLogout.setActionCommand("logout");
        menu.add(itemLogout);
        menuBar.add(menu);
        
        this.setJMenuBar(menuBar);
        
        itemLogout.addActionListener(controller);
        
        
        // Panel welcome
        JPanel p1 = new JPanel();
        FlowLayout topLayout = new FlowLayout(); 
        p1.setLayout(topLayout); 
        JLabel l = new JLabel("Welcome " + this.fullName);
        p1.add(l);
        
        JPanel p2 = new JPanel();
        p2.setBorder(BorderFactory.createTitledBorder("Options"));
        // it is a box layout to model the button in Y AXIS
        BoxLayout lowerLayout = new BoxLayout(p2, BoxLayout.Y_AXIS); 
        p2.setLayout(lowerLayout); 
        
        // button set Slot
        JButton setSlot = new JButton("Set Slots");
        
        setSlot.setActionCommand("setSlot");
        setSlot.addActionListener(controller);
        setSlot.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // button view upcoming Apps
        JButton viewUpcomingApps = new JButton("Upcoming Appointments");
        viewUpcomingApps.setActionCommand("viewUpApps");
        viewUpcomingApps.addActionListener(controller);
        viewUpcomingApps.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        // button set Slot Available or not Available
        JButton setAvailableSlot = new JButton("Set slots as (not) Avaiable");
        setAvailableSlot.setActionCommand("setAvSlot");
        setAvailableSlot.addActionListener(controller);
        setAvailableSlot.setAlignmentX(Component.CENTER_ALIGNMENT);


        // button view apps general
        JButton viewApps = new JButton("View Appointments");
        viewApps.setActionCommand("viewApps");
        viewApps.addActionListener(controller);
        viewApps.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        p2.add(setSlot);
        p2.add(Box.createRigidArea(new Dimension(0, 10)));   
        p2.add(viewUpcomingApps);
        p2.add(Box.createRigidArea(new Dimension(0, 10)));   
        p2.add(setAvailableSlot);
        p2.add(Box.createRigidArea(new Dimension(0, 10)));   
        p2.add(viewApps);
        
        
        this.add(p1, BorderLayout.PAGE_START);
        this.add(p2, BorderLayout.CENTER);
        
    }

    private void validation() {
        this.validate();
        this.repaint();
    }
        
    
}
