/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerSetSlots;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 * this class is used to draw screen for setting slots for a barber
 * @author Fernando
 */
public class SetSlotsScreen extends JFrame {
    
    private ControllerSetSlots controller;
    private JSpinner timeSpinner;
    private DefaultListModel<String> listModel;
    private int index;
    private JList<String> timeList;

    public SetSlotsScreen(ControllerSetSlots controllerSetSlots) {
        this.controller = controllerSetSlots;
        this.index = 0;
        
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
        
    }
    
    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(420,350);
        this.setTitle("Barber Application");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
       
    }

    private void components() {
        
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
        
        // Panel Title
        JPanel p1 = new JPanel();
        FlowLayout topLayout = new FlowLayout(); 
        p1.setLayout(topLayout);
        JLabel l = new JLabel("Enter free available slots");
        p1.add(l);
        
        // Panel center of layout
        JPanel centerPanel = new JPanel();
        // 1 row two columns
        GridLayout centerLayout = new GridLayout(1,2); 
        centerPanel.setLayout(centerLayout);
        
        // Spinner to choose the slot
        // 1st columns
        FlowLayout spinnerLayout = new FlowLayout(); 
        spinnerLayout.setAlignment(FlowLayout.CENTER);
        JPanel spinnerPanel = new JPanel(); 
        spinnerPanel.setLayout(spinnerLayout); 
        
       
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        // set format to HH:mm
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");   
        timeSpinner.setEditor(timeEditor);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        // it will only show the current time
        timeSpinner.setValue(calendar.getTime()); 
        
        spinnerPanel.add(timeSpinner);
        
        // Panel List with times (NO EDITABLE)
        //create the model 
        // 2nd column
        FlowLayout listLayout = new FlowLayout(); 
        listLayout.setAlignment(FlowLayout.CENTER);
        JPanel listPanel = new JPanel(); 
        listPanel.setLayout(listLayout); 
        listModel = new DefaultListModel<>();
        //create the list
        timeList = new JList<>(listModel);        
        listPanel.add(timeList); 
        
        // scroll for the list
        centerPanel.add(spinnerPanel);
        centerPanel.add(new JScrollPane(timeList));
        
        // Buttons below the table
        FlowLayout buttonsLayout = new FlowLayout(); 
        buttonsLayout.setAlignment(FlowLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(buttonsLayout);
        JButton add = new JButton("Add new slot");
        add.setActionCommand("add");
        add.addActionListener(controller);
        JButton delete = new JButton("Delete slot");
        delete.setActionCommand("delete");
        delete.addActionListener(controller);
        JButton save = new JButton("Save slots");
        save.setActionCommand("save");
        save.addActionListener(controller);
        
        buttonsPanel.add(add);
        buttonsPanel.add(delete);
        buttonsPanel.add(save);
        
        // title
        this.add(p1, BorderLayout.PAGE_START); 
        // list
        this.add(centerPanel, BorderLayout.CENTER); 
        // buttons
        this.add(buttonsPanel, BorderLayout.PAGE_END);
        
        
    }

    private void validation() {
        this.validate();
        this.repaint();
    }
    
    // get slot from spinner
    public String getSpinnerText() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(timeSpinner.getValue());
        
    }
    
    // add slot to the list
    public void addSlot(String date) {
        
        listModel.add(index, date);
        index += 1;
        
    }
    
    // get slots selected
    public int getSelectedIndex() {
        
        return timeList.getSelectedIndex();
        
    }
   
   // delete slot
   public void deleteSlot() {
       
        listModel.remove(timeList.getSelectedIndex());
        index -= 1;
       
   }
   
    // Show message on the screen with a popup
    public void showErrorMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.ERROR_MESSAGE);
         
    }
    
    // Show message on the screen with a popup
    public void showInfoMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
         
    }  

    // Show message on the screen with a popup
    public void showSuccessMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
         
    }      
    
    // get all the slots entered by the user
    public String[] getAllSlots() {
        
        String[] slots = new String[listModel.getSize()];
        
        for (int i = 0; i < listModel.getSize(); i++) {
            
            slots[i] = listModel.getElementAt(i);
            
        }
        
        return slots;
        
    }
    
}
