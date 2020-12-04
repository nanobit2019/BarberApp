/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import controller.ControllerCustomerHomeScreen;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * this class is used to represent the home screen for the customer
 * @author Fernando
 */
public class CustomerHomeScreen extends JFrame {
    
    private JButton makeApp;
    private JButton viewApps;
    private String fullName;

    private ControllerCustomerHomeScreen controller;
    
    public CustomerHomeScreen(ControllerCustomerHomeScreen controller, String fullName) {
        
        this.controller = controller;
        this.fullName = fullName;
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(300,170);
        this.setTitle("Home Screen");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(controller);

    }

    private void components() {
        
        
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
        FlowLayout lowerLayout = new FlowLayout(); 
        p2.setLayout(lowerLayout); 
        
        // button makeApp
        makeApp = new JButton("Make an Appointment");
        makeApp.setActionCommand("make");
        makeApp.addActionListener(controller);
        
        // button viewApps
        viewApps = new JButton("View Appointments");
        viewApps.setActionCommand("view");
        viewApps.addActionListener(controller);
        
        p2.add(makeApp);
        p2.add(viewApps);
        
        this.add(p1, BorderLayout.PAGE_START);
        this.add(p2, BorderLayout.CENTER);
        
    }

    private void validation() {
        this.validate();
        this.repaint();
    }
        
    
}
