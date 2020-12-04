/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionListener;
import view.SearchBarbersScreen;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import model.Model;
import model.User;

/**
 * this class is the controller for search barber screen
 * this class is the brain for the screen
 * it handles the events performed by the user 
 * @author Fernando
 */
public class ControllerSearchBarbers extends WindowAdapter implements ActionListener {
    
    private SearchBarbersScreen view;
    private Model model;
    private User user;
    
    public ControllerSearchBarbers(User user){
        this.view = new SearchBarbersScreen(this);
        this.user = user;
        this.model = new Model();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("searchByName")) {
                
            if (view.getNameText() != null && !view.getNameText().equals("")) {
                // search barbers by name. We ue the model to get the data from DB
                List<User> barbers = model.getBarbersByName(view.getNameText());
                Object [][] data = new Object[barbers.size()][4];
                
                int index = 0;
                for (User user: barbers) {
                    
                    data[index][0] = user.getEmail();
                    data[index][1] = user.getfName() + " " + user.getlName();
                    data[index][2] = user.getPhoneNumber();
                    data[index][3] = user.getLocation();
                    
                    index++;
                    
                }
                
                view.setData(data);
                
            }
                
        } else if (e.getActionCommand().equals("searchByLocation")) {

               // not implemented
        
        } else if (e.getActionCommand().equals("makeApp")) {
            
            String[] barberSelected = view.getBarberSelected();
            
            if (barberSelected == null) {

                    view.showInformationMsg("Please, you must select an appointment");
                
            } else {
                    // split full name
                    String[] name = barberSelected[1].split(" ", 2);
                    // create barber
                    User barberResult = new User(barberSelected[0], name[0], name[1], barberSelected[2], "barber", barberSelected[3]);
                    // create controller and screen to make the appointment
                    new ControllerMakeApp(user, barberResult);

            }
            
        }
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
          this.view = null;
          this.model = null;
          this.user = null;
    }
    
}
