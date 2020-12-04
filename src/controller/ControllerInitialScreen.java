/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import view.InitialScreen;
import java.awt.event.ActionEvent;

/**
 * this class is the controller for initial screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerInitialScreen implements ActionListener {
    
    private InitialScreen view;
    
    
    public ControllerInitialScreen(){
        this.view = new InitialScreen(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // login action
        if (e.getActionCommand().equals("login")) {
                
                view.dispose();
                new ControllerLogin();
        // register barber action        
        } else if (e.getActionCommand().equals("registerServiceProvider")) {

                view.dispose();
                new ControllerRegisterBarber();
        // register customer action
        } else if (e.getActionCommand().equals("registerCustomer")) {
            
                view.dispose();
                new ControllerRegisterCustomer();

        }
    }
    
}
