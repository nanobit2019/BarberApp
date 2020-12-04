/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import controller.ControllerRegisterCustomer;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * this class is used to draw the register screen for customer
 * @author Fernando
 */
public class RegisterCustomerScreen extends JFrame {
    
    private ControllerRegisterCustomer controller;
    private JTextField emailText, firstNameText, lastNameText, phoneNumberText;
    private JPasswordField passwordText, confirmPassText;


    public RegisterCustomerScreen(ControllerRegisterCustomer controller) {
        this.controller = controller;
        
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
        
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(400,330);
        this.setTitle("Customer Registration");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(controller);

    }

    private void components() {

        
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
        
        JLabel regFormLabel, emailLabel, passwordLabel, confirmPassLabel, firstNameLabel, lastNameLabel, 
               phoneNumberLabel, mandatoryLabel;

        // Labels
        regFormLabel = new JLabel("Registration Form");
        emailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        confirmPassLabel = new JLabel("Confirm Password: ");
        firstNameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        phoneNumberLabel = new JLabel("Phone Number: ");
        mandatoryLabel = new JLabel("All the fields are mandatory");
        
        // TextFields
        emailText = new JTextField(15);
        passwordText = new JPasswordField(15);
        confirmPassText = new JPasswordField(15);
        firstNameText = new JTextField(15);
        lastNameText = new JTextField(15);
        phoneNumberText = new JTextField(15);
        
        // Button
        JButton register = new JButton("Register");
        register.setActionCommand("register");
        register.addActionListener(controller);
        
        JPanel panelReg = new JPanel();
        GridBagLayout layoutReg = new GridBagLayout();
        panelReg.setLayout(layoutReg);
        GridBagConstraints constraintsReg = new GridBagConstraints();
        
        constraintsReg.insets = new Insets(10, 0, 0, 0);
        constraintsReg.anchor = GridBagConstraints.LINE_START;
        constraintsReg.gridx= 0;
        constraintsReg.gridy= 0;
        panelReg.add(regFormLabel, constraintsReg);

        constraintsReg.gridx= 0;
        constraintsReg.gridy= 1;
        panelReg.add(emailLabel, constraintsReg);        

        constraintsReg.gridx= 1;
        constraintsReg.gridy= 1;
        panelReg.add(emailText, constraintsReg);  
        
        constraintsReg.gridx= 0;
        constraintsReg.gridy= 2;
        panelReg.add(passwordLabel, constraintsReg);  
        
        constraintsReg.gridx= 1;
        constraintsReg.gridy= 2;
        panelReg.add(passwordText, constraintsReg);
        
        constraintsReg.gridx= 0;
        constraintsReg.gridy= 3;
        panelReg.add(confirmPassLabel, constraintsReg);
        
        constraintsReg.gridx= 1;
        constraintsReg.gridy= 3;
        panelReg.add(confirmPassText, constraintsReg);
                
        constraintsReg.gridx= 0;
        constraintsReg.gridy= 4;
        panelReg.add(firstNameLabel, constraintsReg);
        
        constraintsReg.gridx= 1;
        constraintsReg.gridy= 4;
        panelReg.add(firstNameText, constraintsReg);
                        
        constraintsReg.gridx= 0;
        constraintsReg.gridy= 5;
        panelReg.add(lastNameLabel, constraintsReg);
        
        constraintsReg.gridx= 1;
        constraintsReg.gridy= 5;
        panelReg.add(lastNameText, constraintsReg);        

        constraintsReg.gridx= 0;
        constraintsReg.gridy= 6;
        panelReg.add(phoneNumberLabel, constraintsReg);
        
        constraintsReg.gridx= 1;
        constraintsReg.gridy= 6;
        panelReg.add(phoneNumberText, constraintsReg);           

        constraintsReg.gridx= 1;
        constraintsReg.gridy= 7;
        panelReg.add(register, constraintsReg); 
        
        constraintsReg.gridx= 0;
        constraintsReg.gridy= 9;
        panelReg.add(mandatoryLabel, constraintsReg);         
        
        this.add(panelReg, BorderLayout.PAGE_START);
    }

    private void validation() {
        
        this.validate();
        this.repaint();
        
    }
   
    public String getEmailText() {
        return emailText.getText();
    }

    public String getPasswordText() {
        // passwordText.getText() was deprecated
        return new String(passwordText.getPassword());
    }

    public String getConfirmPassText() {
        // passwordText.getText() was deprecated
        return new String(confirmPassText.getPassword());
    }

    public String getFirstNameText() {
        return firstNameText.getText();
    }

    public String getLastNameText() {
        return lastNameText.getText();
    }

    public String getPhoneNumberText() {
        return phoneNumberText.getText();
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

    public void resetTextFields() {
        
        emailText.setText("");
        passwordText.setText("");
        confirmPassText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        phoneNumberText.setText("");
        
    }
    
    
    
}
