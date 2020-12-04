/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import view.MakeAppScreen;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;
import model.Model;
import model.Slot;
import model.User;

/**
 * this class is the controller for make appoiment screen for the customer
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerMakeApp extends WindowAdapter implements ActionListener {
    
    private MakeAppScreen view;
    private User barber;
    private User customer;
    private Model model;
    
    
    public ControllerMakeApp(User customer, User barber) {
        this.barber = barber;
        this.customer = customer;
        // we create the screen and we pass details about the barber
        this.view = new MakeAppScreen(this, barber.getfName() + " " + barber.getlName(), barber.getLocation(), 
                                      barber.getPhoneNumber());
        this.model = new Model();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("book")) {
            
            // date and slot selected are not empty
            if (!view.getDateSelected().equals("") && view.getTimeSelected() != null &&
                !view.getTimeSelected().equals("")) {
                
                // format date to localdate
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d-MM-yyyy");
                LocalDate date = LocalDate.parse(view.getDateSelected(), formatterDate);

                // format time
                LocalTime time = LocalTime.parse(view.getTimeSelected());

                // get appointments for a date and the barber 
                List<Appointment> apps = this.model.getAppoinmentsByDateAndBarber(date, barber);
                
                // check if the appointment is still available
                // because when user select a date, the available slots are displayed
                // then when user click on makeappointment button another check if the 
                // slot is available is done
                if (!slotIsAlreadyTaken(time, apps)) {
                
                    // create appointment
                    Appointment app = new Appointment(customer, barber, "received", "", date, time);
                    
                    // result from insertion
                    String result = model.createAppointment(app);
                    
                    if (result == null)
                        view.showInfoMsg("The apppoinment was created successfully");
                    else 
                        view.showInfoMsg("The appointment was not created: " + "error: " + result);
                                            
                } else {
                    
                   view.showInfoMsg("The slot was taken, please select another one or another date");
   
                }
                        
            } else {
                
                view.showInfoMsg("Please enter values for date and time fields");
                
            }
                
             
                
        } 
    }

    @Override
    public void windowClosing(WindowEvent e) {
          this.view = null;
          this.model = null;
          this.barber = null;
          this.customer = null;
    }
    
    // get slots free to display them on the screen and combobox
    public String[] getSlotsFree() {
        
        List<String> availableSlotAux = new ArrayList<>();        
        
        if (!view.getDateSelected().equals("")) {
            
            List<Appointment> apps;
            List<Slot> slots;
            
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d-MM-yyyy");
            LocalDate date = LocalDate.parse(view.getDateSelected(), formatterDate);
            // get appointments for that barber and date
            apps = this.model.getAppoinmentsByDateAndBarber(date, barber);
            // get available slots for that barber 
            slots = this.model.getSlotsAvailable(barber);
            // loop to get available slot for a specific date
            for (Slot slot: slots) {
                
              if (!slotIsAlreadyTaken(slot.getTime(), apps)) {
                    
                  availableSlotAux.add(slot.getTime().toString());
                    
               }
                
            }
            
        }                
        
        
        return availableSlotAux.toArray(new String[0]);    
        
    }
    // method to check if the slot was taken by another appointment
    private boolean slotIsAlreadyTaken(LocalTime time, List<Appointment> apps) {

        for(Appointment app: apps) {
            
           if (time.compareTo(app.getTime()) == 0) {
               
               return true;
               
           } 
            
        }
        
        return false;

    }
    
}
