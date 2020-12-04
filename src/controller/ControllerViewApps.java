/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import view.ViewAppsScreen;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import model.Appointment;
import model.Model;
import model.User;

/**
 * this class is the controller for view appointment screen for the user
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerViewApps extends WindowAdapter implements ActionListener  {
    
    private ViewAppsScreen view;
    private Model model;
    private User customer;
    List<Appointment> apps;

    public ControllerViewApps(User customer){
        this.model = new Model();
        this.customer = customer;
        
        apps = model.getAppoinmentsByCustomer(this.customer);
        
        Object [][] data = new Object[apps.size()][7];
        
        User barber;
        
        // we should query the database only once to get all the information from barbers
        // we should show the appointments from last month only. 
        // it must be changed
        int index = 0;
        for (Appointment app: apps) {
            
            barber = this.model.getUser(app.getBarber().getEmail());
            
            data[index][0] = app.getId();
            data[index][1] = barber.getfName() + " " + barber.getlName();
            data[index][2] = barber.getPhoneNumber();
            data[index][3] = barber.getEmail();
            data[index][4] = barber.getLocation();
            data[index][5] = app.getStatus();
            data[index][6] = app.getDate().toString() + " - " + app.getTime().toString();
                    
            index++;
            
        }
        
        this.view = new ViewAppsScreen(this, data);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String[] app;
        Appointment appointment;
        // set status to cancelled
        if (e.getActionCommand().equals("cancelled")) {
            
            app = view.getAppSelected();
            
            if (app == null) { // appointment is null
                
                view.showInformationMsg("Please, you must select an appointment");
                
            } else {
                
                if (app[5].equals("cancelled")) { // if the appoinment was already cancelled, user can
                                                  // not cancel it again
                    
                    view.showInformationMsg("The appointment has already been cancelled");
                    return;
                    
                }
                // user can cancel appointments which are not completed or rejected by the barber
                if (app[5].equals("completed") ||
                    app[5].equals("rejected")) {
                    
                    view.showInformationMsg("The appointment can not be cancelled");
                    return;
                    
                }
                // set status to cancel       
                appointment = new Appointment(Integer.parseInt(app[0]));
                
                appointment.setStatus("cancelled");
                
                String result = this.model.updateStatusAppointment(appointment);
                
                if (result == null) {
                    view.showInformationMsg("The appointment was modified successfully");
                    view.setStatus("cancelled");
                }    
                else
                    view.showErrorMsg("The appointment was not modified successfully. Error = " + result);
                    
                
            }
                
        } else if (e.getActionCommand().equals("feedback")) {
            // set feedback to an appointment
            app = view.getAppSelected();
            
            if (app == null) {
                
                view.showInformationMsg("Please, you must select an appointment");
                
            } else {
                
                app = view.getAppSelected();
                
                appointment = new Appointment(Integer.parseInt(app[0]));
                
                new ControllerFeedback(appointment);
                    
            }
            
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          this.view = null;
          this.model = null;
          this.customer = null;
    }
    
    
}
