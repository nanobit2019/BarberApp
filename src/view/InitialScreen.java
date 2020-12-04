/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import controller.ControllerInitialScreen;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is used to draw the initial screen
 * @author Fernando
 */
public class InitialScreen extends JFrame {
    
    private JButton login;
    private JButton registerCustomer;
    private JButton registerServiceProvider;

    private ControllerInitialScreen controller;
    
    public InitialScreen(ControllerInitialScreen controller) {
        
        this.controller = controller;
        
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(300,150);
        this.setTitle("Barber Application");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }

    private void components() {
        
        // main layout
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
        
        // title on the screen
        JPanel p1 = new JPanel();
        FlowLayout topLayout = new FlowLayout(); 
        p1.setLayout(topLayout); 
        JLabel l = new JLabel("Welcome to Barber Application");
        p1.add(l);
        
        // panel to add buttons
        JPanel p2 = new JPanel();
        FlowLayout lowerLayout = new FlowLayout(); 
        p2.setLayout(lowerLayout); 
           
        login = new JButton("Log in");
        login.setActionCommand("login");
        login.addActionListener(controller);
        
        registerCustomer = new JButton("Register Customer");
        registerCustomer.setActionCommand("registerCustomer");
        registerCustomer.addActionListener(controller);

        registerServiceProvider = new JButton("Register Barber");
        registerServiceProvider.setActionCommand("registerServiceProvider");
        registerServiceProvider.addActionListener(controller);
        
        p2.add(login);
        p2.add(registerServiceProvider);
        p2.add(registerCustomer);
        this.add(p1, BorderLayout.PAGE_START);
        this.add(p2, BorderLayout.CENTER);
        
    }

    private void validation() {
        this.validate();
        this.repaint();
    }
        
}
