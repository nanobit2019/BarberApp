/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.User;
import view.BarberHomeScreen;

/**
 * this class is the controller for barber home screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerBarberHomeScreen extends WindowAdapter implements ActionListener {
    
    private BarberHomeScreen view;
    private User user;
    
    
    public ControllerBarberHomeScreen(User user){
        this.user = user;
        this.view = new BarberHomeScreen(this, user.getfName() + " " + user.getlName());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("setSlot")) {
                
            new ControllerSetSlots(user);
                
        } else if (e.getActionCommand().equals("viewUpApps")) {
            
            new ControllerBarberViewUpApps(user);

            
        } else if (e.getActionCommand().equals("setAvSlot")) {
            
            // not implemented
            
        } else if (e.getActionCommand().equals("viewApps")) {
            
            // not implemented

        } else if (e.getActionCommand().equals("logout")) {
            
            // logout - release object and dispose the screen
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
