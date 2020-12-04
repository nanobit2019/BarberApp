/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class appoinment is used to represent an appoinment in the system
 * @author Fernando
 */
public class Appointment {
    
    private int id;
    private User customer;
    private User barber;
    private String status;
    private String feedback;
    private LocalDate date;
    private LocalTime time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Appointment(User customer, User barber, String status, String feedback, LocalDate date, LocalTime time) {
        this.customer = customer;
        this.barber = barber;
        this.status = status;
        this.feedback = feedback;
        this.date = date;
        this.time = time;
    }

    public Appointment(int id, User customer, User barber, String status, String feedback, LocalDate date, LocalTime time) {
        this.id = id;
        this.customer = customer;
        this.barber = barber;
        this.status = status;
        this.feedback = feedback;
        this.date = date;
        this.time = time;
    }

    public Appointment(int id) {
        this.id = id;
    }
    
    
    
    
    
    
}
