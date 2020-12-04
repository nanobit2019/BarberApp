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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Model;
import model.User;
import view.LoginScreen;
/**
 * this class is the controller for login screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerLogin extends WindowAdapter implements ActionListener {

    private LoginScreen view;
    private Model model;
    // code got from https://stackoverflow.com/questions/8204680/java-regex-email (answered by Jason Buberel)
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    
    public ControllerLogin() {
        
        this.view = new LoginScreen(this);
        this.model = new Model();
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
          this.view = null;
          this.model = null;
          new ControllerInitialScreen();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // action login
        if (e.getActionCommand().equals("login")) {
           
           // validation empty fields
           if (view.getEmailText() != null        && 
              !view.getEmailText().equals("")     && 
               view.getPasswordText()!= null      && 
              !view.getPasswordText().equals("")) {
               
                // validate email format
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(view.getEmailText());
                
                if (!matcher.find()) {
                    
                    view.showErrorMsg("The email format is invalid"); 
                    return;
                    
                }
                // get user
                User user = this.model.getUser(view.getEmailText());
                
                if (user == null) {
                    
                    view.showErrorMsg("The supplied email and/or password did not match any entries in our database"); 
                    return;
                    
                } 
                // check if password are identical
                if (validPassword(view.getPasswordText(), user.getPassword())) {

                    view.showSuccessMsg("The user is logged into the system successfully");         
                    // release the objects used by the object
                    view.dispose();
                    this.view = null;
                    this.model = null;
                    
                    if (user.getType().equals("customer")) {
                        
                        new ControllerCustomerHomeScreen(user);

                    } else {
                        
                        new ControllerBarberHomeScreen(user);
                    }
                    

                } else {

                    view.showErrorMsg("Password is incorrect");         

                }
                    
           } else {
               
               view.showErrorMsg("Email and/or password field are empty. Please, enter both");         
               
           }

        }

    }
    // valid password using method BCrypt    
    public Boolean validPassword(String password, String passwordFromDB) {
        
        // check password entered by user against password from database
	return BCrypt.checkpw(password, passwordFromDB);
        
    }
       
}
