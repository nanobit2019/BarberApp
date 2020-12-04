/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import view.CustomerHomeScreen;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.User;

/**
 * this class is the controller for customer home sreen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerCustomerHomeScreen extends WindowAdapter implements ActionListener {
    
    private CustomerHomeScreen view;
    private User user;
    
    
    public ControllerCustomerHomeScreen(User user){
        this.user = user;
        this.view = new CustomerHomeScreen(this, user.getfName() + " " + user.getlName());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("make")) {
                
            new ControllerSearchBarbers(user);
                
        } else if (e.getActionCommand().equals("view")) {

            new ControllerViewApps(user);
        
        } else if (e.getActionCommand().equals("logout")) {
            // call dispose method from view to destroy the screen
            this.view.dispose();
            this.view = null;
            this.user = user; 
            new ControllerLogin();
            
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          this.view.dispose();
          this.view = null;
          this.user = user;
          new ControllerLogin();
    }
    
    
    
}
