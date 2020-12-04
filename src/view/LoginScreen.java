/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerLogin;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * this class dran login screen for both users
 * @author Fernando
 */
public class LoginScreen extends JFrame {
    
    private JButton login;
    private ControllerLogin controller;
    private JTextField tf1;
    private JPasswordField tf2;
    
    public LoginScreen(ControllerLogin controller) {
        
        this.controller = controller;
        
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(260,130);
        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(controller);
        

    }

    private void components() {
        
        //main layout
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
        
        // panel with grid layout 2 rows and 1 column
        JPanel mainpanel = new JPanel(); 
        GridLayout mainLayout = new GridLayout(2, 1); 
        mainpanel.setLayout(mainLayout); 

        FlowLayout buttonLayout = new FlowLayout(); 
        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setLayout(buttonLayout); 

        // Email     
        JPanel emailPanel = new JPanel();
        FlowLayout emailLayout = new FlowLayout();
        emailLayout.setAlignment(FlowLayout.LEFT);
        emailPanel.setLayout(emailLayout);
        
        JLabel l1 = new JLabel();
        l1.setText("Email:          ");
        tf1 = new JTextField(15);
        emailPanel.add(l1);
        emailPanel.add(tf1);

        // Password
        JPanel passPanel = new JPanel();
        FlowLayout passLayout = new FlowLayout();
        passLayout.setAlignment(FlowLayout.LEFT);
        passPanel.setLayout(passLayout);
        
        JLabel l2 = new JLabel();
        l2.setText("Password: ");
        tf2 = new JPasswordField(15);
        passPanel.add(l2);
        passPanel.add(tf2);

        // Button
        login = new JButton("Log in");
        login.setActionCommand("login");
        login.addActionListener(controller);

        mainpanel.add(emailPanel);
        mainpanel.add(passPanel);
        
        buttonPanel.add(login); 
        
        this.add(mainpanel, BorderLayout.PAGE_START); 

        this.add(buttonPanel, BorderLayout.PAGE_END); 

    }

    private void validation() {
        this.validate();
        this.repaint();
    }

    public String getEmailText() {
        return tf1.getText();
    }

    public String getPasswordText() {
        return new String(tf2.getPassword());
    }

    
    // Show message on the screen with a popup
    public void showErrorMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.ERROR_MESSAGE);
         
    }
    
    // Show message on the screen with a popup
    public void showSuccessMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
         
    }  

    
    
    
    
}
