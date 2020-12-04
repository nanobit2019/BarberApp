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
import model.Appointment;
import model.Model;
import view.FeedbackScreen;

/**
 * this class is the controller for feedback screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerFeedback extends WindowAdapter implements ActionListener  {
    
    private FeedbackScreen view;
    private Model model;
    private Appointment app;

    public ControllerFeedback(Appointment app) {
        this.view = new FeedbackScreen(this);
        this.app = app;
        this.model = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("feedback")) {
            
            String feedback = view.getFeedback();
            
            if (feedback.equals("")) {
                
                view.showInformationMsg("Feedback field is empty");
                return;
            }
            // check if characters in the text are greater than 300
            if (feedback.length() > 300) {
                
                view.showInformationMsg("Feedback field is too long. Maximum is 300 characters");
                return;
                
            }
            
            app.setFeedback(feedback);
            
            String result = this.model.updateFeedbackAppointment(app);
                
            if (result == null)
                view.showSuccessMsg("The appointment was modified successfully");
            else
                view.showErrorMsg("The appointment was not modified successfully. Error = " + result);          
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          this.view = null;
          this.model = null;
          this.app = null;
    }
    
    
    
    
}
