/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.Model;
import model.User;
import view.RegisterBarberScreen;

/**
 * this class is the controller for register screen for the barber
 * this class is the brain for the screen
 * it handles the events performed by the user
 * @author Fernando
 */
public class ControllerRegisterBarber extends WindowAdapter implements ActionListener {

    private RegisterBarberScreen view;
    private Model model;

    public ControllerRegisterBarber() {
        this.view = new RegisterBarberScreen(this);
        this.model = new Model();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("register")) {
                
            if (view.getEmailText() != null          && 
               !view.getEmailText().equals("")       && 
                view.getFirstNameText() != null      && 
               !view.getFirstNameText().equals("")   && 
                view.getLastNameText() != null       &&
               !view.getLastNameText().equals("")    &&
                view.getPasswordText() != null       &&
               !view.getPasswordText().equals("")    &&
                view.getConfirmPassText() != null    &&
               !view.getConfirmPassText().equals("") &&
                view.getPhoneNumberText() != null    &&
               !view.getPhoneNumberText().equals("") &&
                view.getLocationText() != null       &&
               !view.getLocationText().equals(""))               
            {
                // I also must validate that the password respect lenght and contains speacial characters
                // check mobile phone format
                // check the email has the right format, etc... 
                if (view.getPasswordText().equals(view.getConfirmPassText())) {
                
                    // create user to register
                    User user = new User(view.getEmailText(), view.getFirstNameText(), view.getLastNameText(), 
                                    view.getPhoneNumberText(), "barber", 
                                    generatePasswordHashedAndSalted(view.getPasswordText()), view.getLocationText());
                    
                    // call model to insert the user in the database
                    String resultMessage = model.createUser(user);
                    
                    if (resultMessage == null) { // if result is null, the user was registered successfully
                        
                        view.resetTextFields();
                        view.showSuccessMsg("The user was created successfully");
                        user = null;
                        
                    } else {
                        // we should identify the most important errors and return them
                        view.showErrorMsg("Creation was not successfull");
                        
                    }
                    
                } else { // password fields don't match
                    
                    view.showErrorMsg("Password and confirm password do not match");

                    
                }
                
                
            } else {
                
               view.showErrorMsg("Every field is mandatory, they must be filled");
                
            }
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          this.model = null;
          this.view = null;
          new ControllerInitialScreen();
    }

    // call the method which has the call to the BCrypt method   
    public String generatePasswordHashedAndSalted(String password) {
        
        return generateSecuredPasswordHash(password, generateSalt());
          
    }
    
    
    // genete a hashed and salted password from a password        
    public String generateSecuredPasswordHash(String password, String salt) {
        
        return BCrypt.hashpw(password, salt);
        
    } 
    
    // generate salt
    public String generateSalt() {
        
        return BCrypt.gensalt(12);
        
    }
        
}
