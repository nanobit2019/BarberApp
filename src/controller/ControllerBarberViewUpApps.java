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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;
import model.Model;
import model.User;
import view.BarberViewUpAppsScreen;

/**
 * this class is the controller for upcoming appointment screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerBarberViewUpApps extends WindowAdapter implements ActionListener  {
    
    private BarberViewUpAppsScreen view;
    private Model model;
    private User barber;
    List<Appointment> apps = new ArrayList<>();

    public ControllerBarberViewUpApps(User barber) {
        
        this.model = new Model();
        this.barber = barber;
                
        // get the upcomming apppointments (the following 2 days) 
        apps = model.getAppoinmentsByBarberAndDates(barber, LocalDate.now(), LocalDate.now().plusDays(1));
        
        List<String> customerEmails = getEmails();
        
        List<User> customers = model.getUsers(customerEmails);
        
        Object [][] data = new Object[apps.size()][6];
        
        User customer;
        
        // we should query the database only once to get all the information for barbers
        int index = 0;
        for (Appointment app: apps) {
            
            customer = searchCustomer(customers, app.getCustomer().getEmail());
            
            data[index][0] = app.getId();
            data[index][1] = customer.getfName() + " " + customer.getlName();
            data[index][2] = customer.getPhoneNumber();
            data[index][3] = customer.getEmail();
            data[index][4] = app.getStatus();
            data[index][5] = app.getDate().toString() + " " + app.getTime().toString();
                    
            index++;
            
        }
        // create the screen with the upcoming appointments 
        this.view = new BarberViewUpAppsScreen(this, data);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String[] app;
        Appointment appointment;
        // change status events
        if (e.getActionCommand().equals("rejected")  ||
            e.getActionCommand().equals("confirmed") ||
            e.getActionCommand().equals("arrived") ||
            e.getActionCommand().equals("completed")) {
            
            app = view.getAppSelected();
            
            if (app == null) { // if the user selects an appointment
                
                view.showInformationMsg("Please, you must select an appointment");

            } else {
                
                if (app[4].equals("cancelled")) { // if the appointment was cancelled,el barber can't change the status
                    
                    view.showInformationMsg("The appointment was cancelled by the customer");
                    return;                    
                    
                }
                // the barber can't change an appointmnet from a state to same state
                if ((e.getActionCommand().equals("rejected") && app[4].equals("rejected"))    ||
                    (e.getActionCommand().equals("confirmed") && app[4].equals("confirmed"))  ||
                    (e.getActionCommand().equals("arrived") && app[4].equals("arrived"))      || 
                    (e.getActionCommand().equals("completed") && app[4].equals("completed"))) { 
                
                    view.showInformationMsg("The appointment has already changed");
                    return;
                    
                }
                // new appointment
                appointment = new Appointment(Integer.parseInt(app[0]));
                // new status
                appointment.setStatus(e.getActionCommand());
                // update DB
                String result = this.model.updateStatusAppointment(appointment);
                
                if (result == null) {
                    view.showInformationMsg("The appointment was modified successfully");
                    view.setStatus(e.getActionCommand());
                }    
                else
                    view.showErrorMsg("The appointment was not modified successfully. Error = " + result);
                
            }
            
        } else if (e.getActionCommand().equals("refresh")) {
         
            // not implemented
            
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          this.view = null;
          this.model = null;
          this.barber = null;
    }
    
    // get customer emails from a the appointments displayed on the screen
    private List<String> getEmails() {

        List<String> emails = new ArrayList<>();
        
        for(Appointment app: apps) {
            
            emails.add(app.getCustomer().getEmail());
            
        }

        return emails;
        
    }
    
    // get customer searching by email
    private User searchCustomer(List<User> customers, String email) {

        
        for(User customer: customers) {
            
            if (email.equals(customer.getEmail())) {
                
                return customer;
                
            }
            
        }
        
        return null;
        
    }
    
    
}
