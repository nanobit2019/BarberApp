/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerMakeApp;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * this class is used create the make app screen
 * @author Fernando
 */
public class MakeAppScreen extends JFrame {
    
    private JButton book;
    private ControllerMakeApp controller;
    private String barberName;
    private String barberLocation;
    private String barberPhoneNumber;   
    private JTextField textFieldDate;
    private JComboBox<String> slots;
    // old value to save the old value for the date field, if it is changed
    // the old value is saved in this parameter
    private String oldValueDate;
    
    public MakeAppScreen(ControllerMakeApp controller, String barberName, String barberLocation,
                        String barberPhoneNumber) {
                
        this.controller = controller;
        this.barberName = barberName;
        this.barberLocation = barberLocation;
        this.barberPhoneNumber = barberPhoneNumber;
        this.oldValueDate = "";
        
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(500,180);
        this.setTitle("Barber Application");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(controller);

    }

    private void components() {
        
        // main layout
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
                
        // title
        JPanel p1 = new JPanel();
        GridLayout barberInfoLayout = new GridLayout(3,1); 
        p1.setLayout(barberInfoLayout);
        
        // panel to add personal details about the barber
        JPanel p2 = new JPanel();
        FlowLayout p2Layout = new FlowLayout();
        p2Layout.setAlignment(FlowLayout.LEFT);
        JLabel l2 = new JLabel("Barber Name: " + barberName);
        p2.add(l2);
        
        JPanel p3 = new JPanel();
        FlowLayout p3Layout = new FlowLayout();
        p3Layout.setAlignment(FlowLayout.LEFT);
        JLabel l3 = new JLabel("Location: " + barberLocation);
        p3.add(l3);
        
        JPanel p4 = new JPanel();
        FlowLayout p4Layout = new FlowLayout();
        p4Layout.setAlignment(FlowLayout.LEFT);
        JLabel l4 = new JLabel("Phone Number: " + barberPhoneNumber );
        p4.add(l4);
                
        p1.add(p2);
        p1.add(p3);
        p1.add(p4);
        
        // add calendar and slots 1 row and 2 columns
        JPanel p5 = new JPanel();
        GridLayout layoutslot = new GridLayout(1,2); 
        p5.setLayout(layoutslot);
        
        JPanel p6 = new JPanel();
        FlowLayout calendarLayout = new FlowLayout();
        calendarLayout.setAlignment(FlowLayout.LEFT);
        p6.setLayout(calendarLayout);
        
        // 1st column
        // create text field to show the date selected 
	textFieldDate = new JTextField();
        textFieldDate.setColumns(10);
        
        // create button to open date picker screen
        JButton pickDateButton = new JButton("Pick a date");
        pickDateButton.setPreferredSize(new Dimension(100, 20));

        //perform action listener
	pickDateButton.addActionListener(new ActionListener() {	
			
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
		//create frame new object  f
		final JFrame datePicker = new JFrame();
		//set text which is collected by date picker i.e. set date 
		textFieldDate.setText(new DatePicker(datePicker).setPickedDate());
                
                // if it is empty I dont have to look for slots
                // user could click on a blank space, and no date is selected
                if (textFieldDate.getText().equals("")) 
                    return;
                
                // format date
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("d-MM-yyyy");
                LocalDate date = LocalDate.parse(textFieldDate.getText(), formatterDate);
                
                if (date.compareTo(LocalDate.now()) < 0) { // compare if the date selected is before today
                    
                    showInfoMsg("The selected date is before today. Please select another date");
                    slots.removeAllItems();
                    return;
                    
                }
                // the user select a new value, different from previous one
                if (!textFieldDate.getText().equals(oldValueDate) && (!textFieldDate.getText().equals(""))) {
                                        
                        slots.removeAllItems();
                        // get slots from controller to show the new slots for the selected date 
                        String[] freeSlots = controller.getSlotsFree();
                        for(String s: freeSlots) {
                            
                            slots.addItem(s);
                            
                        }               
                }
                
            }
	});
        
        p6.add(textFieldDate);
        p6.add(pickDateButton);
        
        // 2nd column
        String s1[] = {};
        JPanel p7 = new JPanel();
        FlowLayout slotLayout = new FlowLayout();
        
        calendarLayout.setAlignment(FlowLayout.LEFT);
        
        JLabel slotLabel = new JLabel("select a slot: ");  
        p7.setLayout(slotLayout);
        // combobox with slots
        slots = new JComboBox<>(s1);
        
        p7.add(slotLabel);
        p7.add(slots);
        
        p5.add(p6);
        p5.add(p7);
        
        // make app button
        JPanel p8 = new JPanel();
        FlowLayout bookButtonLayout = new FlowLayout();
        bookButtonLayout.setAlignment(FlowLayout.CENTER);
        book = new JButton("Book appointment");
        book.setPreferredSize(new Dimension(140, 20));
        book.setActionCommand("book");
        book.addActionListener(controller);
        p8.add(book);
        
        this.add(p1, BorderLayout.PAGE_START);
        this.add(p5, BorderLayout.CENTER);
        this.add(p8, BorderLayout.PAGE_END);
        

                
    }
    

    private void validation() {
        this.validate();
        this.repaint();
    }
    
    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public void setBarberLocation(String barberLocation) {
        this.barberLocation = barberLocation;
    }

    public void setBarberPhoneNumber(String barberPhoneNumber) {
        this.barberPhoneNumber = barberPhoneNumber;
    }

    // get selected date from field on the screen 
    public String getDateSelected() {
        
        return this.textFieldDate.getText();
        
    }
    
    // get selected slots 
    public String getTimeSelected() {
        
        if (!(this.slots.getSelectedItem() == null)) // if it is not null, send it
            return this.slots.getSelectedItem().toString();
        else
            return "";
    }
    
        // Show message on the screen with a popup
    public void showInfoMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
         
    }  
    
}



