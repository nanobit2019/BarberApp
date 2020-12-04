/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerFeedback;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Fernando
 */
public class FeedbackScreen extends JFrame {
    
    private ControllerFeedback controller;
    private JTextArea feedbackTextArea;
    
    public FeedbackScreen(ControllerFeedback controller) {
        
        this.controller = controller;
        attributesSetter();
        components();
        validation();
        
    }
    
    private void attributesSetter() {

        this.setVisible(true);
        this.setSize(350,250);
        this.setTitle("Barber Application");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    private void components() {

        BorderLayout frameLayout = new BorderLayout();
        this.setLayout(frameLayout);
                
        // title
        JPanel p1 = new JPanel();
        FlowLayout titleLayout = new FlowLayout(); 
        titleLayout.setAlignment(FlowLayout.CENTER);
        p1.setLayout(titleLayout); 
        JLabel l1 = new JLabel("Enter Feedback");
        p1.add(l1);
        
        
        // add center
        // panel to enter feedback
        JPanel p3 = new JPanel();
        FlowLayout centerLayout = new FlowLayout(); 
        titleLayout.setAlignment(FlowLayout.CENTER);
        p1.setLayout(titleLayout); 
        feedbackTextArea = new JTextArea();
        feedbackTextArea.setColumns(20);
        feedbackTextArea.setLineWrap (true);
        feedbackTextArea.setRows(8);
        JScrollPane scrollpane = new JScrollPane(feedbackTextArea);
        p3.add(scrollpane);        

        
        // add Button send feeback
        JPanel p2 = new JPanel();
        FlowLayout pageEndLayout = new FlowLayout(); 
        pageEndLayout.setAlignment(FlowLayout.CENTER);
        p2.setLayout(pageEndLayout); 
        JButton feedback = new JButton("Send Feedback");
        feedback.setActionCommand("feedback");
        feedback.addActionListener(controller);        
        p2.add(feedback);
        
        this.add(p1, BorderLayout.PAGE_START);
        this.add(p3, BorderLayout.CENTER);
        this.add(p2, BorderLayout.PAGE_END);
    }

    private void validation() {
        
        this.validate();
        this.repaint();

    }
    
    public void showSuccessMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    // Show message on the screen with a popup
    public void showErrorMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        JOptionPane.ERROR_MESSAGE);
         
    }
    
    public void showInformationMsg(String message) {
        
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    // get feedback from textarea component
    public String getFeedback() {
        
        return feedbackTextArea.getText();
        
    }
        
}
