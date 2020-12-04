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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.Slot;
import model.User;
import view.SetSlotsScreen;

/**
 * this class is the controller for set slots screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerSetSlots extends WindowAdapter implements ActionListener {
    
    private SetSlotsScreen view;
    private Model model;
    private User barber;
    
    
    public ControllerSetSlots(User barber) {
        this.view = new SetSlotsScreen(this);
        this.model = new Model();
        this.barber = barber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            
        if (e.getActionCommand().equals("add")) {
            
            // get all the slots to check if the new slot was already added
            String date = view.getSpinnerText();
            String[] slots = view.getAllSlots();
            
            for (String s: slots) {
                
                if (s.equals(date)) {
                    
                    view.showErrorMsg("This slot have been already added");
                    return;
                    
                }
                
            }
            // add slot
            if (!date.equals(""))
                view.addSlot(date);
        
        } else if (e.getActionCommand().equals("delete")) {
            
            // delete slot from list of slots
            if (view.getSelectedIndex() >= 0)
                view.deleteSlot();
            
        } else if (e.getActionCommand().equals("save")) {
            
            // save all the slots entered by the user
            String[] slots = view.getAllSlots();
            
            if (slots.length == 0) {
                
                view.showInfoMsg("You must enter slots to save, the list is empty");
                return;
                
            }
            
            List<Slot> slotsAux = new ArrayList<>();
            
            Slot slot;
            
            for(String s: slots) {
                
                slot = new Slot(barber, LocalTime.parse(s) , true);
                
                slotsAux.add(slot);
                
            }
            // call the model to save the slots
            String result = model.createSlots(slotsAux);
            
            if (result == null) {
                
                view.showSuccessMsg("The slots were created successfully");
                
            } else {
                
                view.showErrorMsg("The slots were not saved. Error: " + result);
                
            }
            
        }

    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          view.dispose();
          this.view = null;
          this.model = null;
          this.barber = null;
    }
        
}
