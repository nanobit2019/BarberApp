/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerSearchBarbers;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * this class is used to dran the screen to search barbers for the customer
 * @author Fernando
 */
public class SearchBarbersScreen extends JFrame {
    
    private JButton searchByName;
    private JButton searchByLocation;
    private JButton makeApp;
    private JTable barbersTable;
    private JTextField nameText;
    private ControllerSearchBarbers controller;
    // data about barbers
    private final Object[] columnNames = {"email", "Barber Name", "Phone Number", "Location"};

    public SearchBarbersScreen(ControllerSearchBarbers controller) {
        
        this.controller = controller;
        
        // We encapsulated the building process of the window
        attributesSetter();
        components();
        validation();
    }

    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(900,550);
        this.setTitle("Barber Application");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addWindowListener(controller);

    }

    private void components() {
        
        // main layout
        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
        
        // page_start panel
        JPanel pageStartPanel = new JPanel();
        // DIVIDING TOP SECTION INTO THREE (THREE ROW - ONE COLUMNS)
        GridLayout pageStartLayout = new GridLayout(3,1);
        pageStartPanel.setLayout(pageStartLayout);
        
        // title
        JPanel p1 = new JPanel();
        FlowLayout titleLayout = new FlowLayout(); 
        titleLayout.setAlignment(FlowLayout.CENTER);
        p1.setLayout(titleLayout); 
        JLabel l1 = new JLabel("Search Barbers or Hairdressers");
        p1.add(l1);
        
        // search area for name text (button and label)
        JPanel p2 = new JPanel();
        FlowLayout searchLayout1 = new FlowLayout(); 
        searchLayout1.setAlignment(FlowLayout.LEFT);
        p2.setLayout(searchLayout1); 
        JLabel l2 = new JLabel("Name:      ");
        nameText = new JTextField(15);
        searchByName = new JButton("Search by name");
        searchByName.setPreferredSize(new Dimension(140, 20));
        searchByName.setActionCommand("searchByName");
        searchByName.addActionListener(controller);
        p2.add(l2);
        p2.add(nameText);
        p2.add(searchByName);
        
        // search area for location text (button and label)
        JPanel p3 = new JPanel();
        FlowLayout searchLayout2 = new FlowLayout(); 
        searchLayout2.setAlignment(FlowLayout.LEFT);
        p3.setLayout(searchLayout2); 
        JLabel l3 = new JLabel("Location: ");
        JTextField locationText = new JTextField(15);
        searchByLocation = new JButton("Search by location");
        searchByLocation.setPreferredSize(new Dimension(140, 20));
        searchByLocation.setActionCommand("searchByName");
        searchByLocation.addActionListener(controller);
        p3.add(l3);
        p3.add(locationText);
        p3.add(searchByLocation);
        
        pageStartPanel.add(p1);
        pageStartPanel.add(p2);
        pageStartPanel.add(p3);
        
        // add center
        // data to populate the table
        Object[][] data = null; 
        
        // Initializing the JTable         
        TableModel tableModel = new TableModel(data, columnNames);
        
        
        barbersTable = new JTable(tableModel);         
        JScrollPane sp = new JScrollPane(barbersTable);
        barbersTable.setSelectionModel(new ForcedListSelectionModel());
        
        // add Button Make an Appointment
        JPanel p4 = new JPanel();
        FlowLayout pageEndLayout = new FlowLayout(); 
        pageEndLayout.setAlignment(FlowLayout.CENTER);
        p4.setLayout(pageEndLayout); 
        makeApp = new JButton("Make an Appointment");
        makeApp.setActionCommand("makeApp");
        makeApp.addActionListener(controller);
        p4.add(makeApp);
        
        this.add(pageStartPanel, BorderLayout.PAGE_START);
        this.add(sp, BorderLayout.CENTER);
        this.add(p4, BorderLayout.PAGE_END);
                
    }
    

    private void validation() {
        this.validate();
        this.repaint();
    }
    
    // get details from selected barber
    public String[] getBarberSelected() {
        
        
        if (barbersTable.getSelectedRow() >= 0) {
            
            String[] barber = new String[4];
            barber[0] = barbersTable.getValueAt(barbersTable.getSelectedRow(), 0).toString();
            barber[1] = barbersTable.getValueAt(barbersTable.getSelectedRow(), 1).toString();
            barber[2] = barbersTable.getValueAt(barbersTable.getSelectedRow(), 2).toString();
            barber[3] = barbersTable.getValueAt(barbersTable.getSelectedRow(), 3).toString();
            return barber;
        }
        return null;           
       
    }
    
    public void showInformationMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.INFORMATION_MESSAGE);
        
        
    }

    public String getNameText() {
       return nameText.getText();
    }
    
    // update table with new information about barbers
    public void setData(Object[][] data) {

        TableModel tableModel = new TableModel(data, columnNames);
        barbersTable.setModel(tableModel);
    }
        
}

// table model to make cell not editable
class TableModel extends DefaultTableModel {
    
    public TableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
              
}


