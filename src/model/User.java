/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * user, it could be a barber or customer
 * @author Fernando
 */
public class User {
    
    private String email;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String type;
    private String password;
    private String location;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User(String email, String fName, String lName, String phoneNumber, String type, String password, String location) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.password = password;
        this.location = location;
    }

    public User(String email, String fName, String lName, String phoneNumber, String type, String location) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.location = location;
    }

    public User(String email) {
        this.email = email;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", fName=" + fName + ", lName=" + lName + ", phoneNumber=" + phoneNumber + '}';
    }


    
    

        
}
