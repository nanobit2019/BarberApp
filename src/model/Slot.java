/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalTime;

/**
 * slot for a barber
 * @author Fernando
 */
public class Slot {
    
    private int id;
    private User barber;
    private LocalTime time;
    private Boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBarber() {
        return barber;
    }

    public void setBarber(User barber) {
        this.barber = barber;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Slot(int id, User barber, LocalTime time, Boolean available) {
        this.id = id;
        this.barber = barber;
        this.time = time;
        this.available = available;
    }

    public Slot(User barber, LocalTime time, Boolean available) {
        this.barber = barber;
        this.time = time;
        this.available = available;
    }
    
    
    
}
