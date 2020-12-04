/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class model used to connect with the DB and query it.
 * @author Fernando
 */
public class Model {
    
    // link to the database
    private String dbServer = "jdbc:mysql://52.50.23.197/Rolando_2019337?use_SSL=false";
    // user to query DB and perorm inserts, deletes and updaes
    private String user = "Rolando_2019337";
    // password
    private String password = "2019337";
    
    public Model() {
        
    }
    
    // Method which get an user from email. it could be and email from barber and customer
    public User getUser(String email) {
        
        String query = "SELECT * FROM user WHERE email = '" + email + "';";
        String firstName, lastName, phoneNumber, type, passwordUser, location;
        User userResult = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            if (rs.next()) {
                
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                phoneNumber = rs.getString("phone_number");
                type = rs.getString("type");
                passwordUser = rs.getString("password");
                location = rs.getString("location");
                        
                userResult = new User(email, firstName, lastName, phoneNumber, type, passwordUser, location);
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return userResult;
                
    }
    
    // create an user in the database, return a string null if there are no error, otherwise return
    // the message from database
    public String createUser(User newUser) {
        
        String sql = "INSERT INTO user (email, first_name, last_name, phone_number, type, password, location) "
                     + "VALUES ('" + newUser.getEmail() + "','" + newUser.getfName() + "','" + newUser.getlName() 
                     + "','" + newUser.getPhoneNumber() + "','" + newUser.getType() + "','" + newUser.getPassword() 
                     + "','" + newUser.getLocation()+ "')";
        
        String resultMessage = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Loop through the SQL Exceptions
            while (se != null) {
                resultMessage = "State  : " + se.getSQLState() +
                                "Message: " + se.getMessage() +
                                "Error  : " + se.getErrorCode() + "\n";

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
       
            return resultMessage;

        } 
                     
    }

    // get barbers by na,e
    public List<User> getBarbersByName(String name) {
        
        // the query is created by filtering by name and barber type
        String query = "SELECT * FROM user WHERE first_name = '" + name + "' AND type = 'barber';";
        String email, firstName, lastName, phoneNumber, type, passwordUser, location;
        List<User> usersResult = new ArrayList<>();
        User userResult = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            while (rs.next()) {
                                
                email = rs.getString("email");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                phoneNumber = rs.getString("phone_number");
                type = rs.getString("type");
                passwordUser = rs.getString("password");
                location = rs.getString("location");
                        
                userResult = new User(email, firstName, lastName, phoneNumber, type, passwordUser, location);
                
                usersResult.add(userResult);
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return usersResult;    
        
        
    }
    
    // create an appointment
    public String createAppointment(Appointment app) {
        
        java.sql.Date date = java.sql.Date.valueOf(app.getDate());
        java.sql.Time time = java.sql.Time.valueOf(app.getTime());
        
        String sql = "INSERT INTO appointment (customer, service_provider, status, feedback, date, time) "
                     + "VALUES ('" + app.getCustomer().getEmail() + "','" + app.getBarber().getEmail()
                     + "','" + app.getStatus() + "','" + "" + "','" + date
                     + "','" + time + "')";
        
        String resultMessage = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Loop through the SQL Exceptions
            while (se != null) {
                resultMessage = "State  : " + se.getSQLState() +
                                "Message: " + se.getMessage() +
                                "Error  : " + se.getErrorCode() + "\n";

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
       
            return resultMessage;

        }         
        
    } 
    
    // update status of an appointment
    public String updateStatusAppointment(Appointment app) {
                
        String sql = "UPDATE appointment SET status = '" + app.getStatus() + "' WHERE id = "
                     + app.getId();
        
        String resultMessage = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Loop through the SQL Exceptions
            while (se != null) {
                resultMessage = "State  : " + se.getSQLState() +
                                "Message: " + se.getMessage() +
                                "Error  : " + se.getErrorCode() + "\n";

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
       
            return resultMessage;

        }         
        
    } 
    
    // update feedback for an appointment
        public String updateFeedbackAppointment(Appointment app) {
                
        String sql = "UPDATE appointment SET feedback = '" + app.getFeedback() + "' WHERE id = "
                     + app.getId();
        
        String resultMessage = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Loop through the SQL Exceptions
            while (se != null) {
                resultMessage = "State  : " + se.getSQLState() +
                                "Message: " + se.getMessage() +
                                "Error  : " + se.getErrorCode() + "\n";

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
       
            return resultMessage;

        }         
        
    } 
    
    // get appointments by barber email and a date provided    
    public List<Appointment> getAppoinmentsByDateAndBarber(LocalDate date, User barber) {
            
        java.sql.Date dateAux = java.sql.Date.valueOf(date);
            
        String query = "SELECT * FROM appointment WHERE service_provider = '" + barber.getEmail() 
                            + "' AND date = '" + dateAux + "';";
        String emailCustomer, status, feedback;
        int id;
        LocalTime time;
        User customer;
        List<Appointment> appsResult = new ArrayList<>();
        Appointment appResult = null;

        try {

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            while (rs.next()) {

            emailCustomer = rs.getString("customer");
            status = rs.getString("status");
            feedback = rs.getString("feedback");
            time = rs.getTime("time").toLocalTime();
            id = rs.getInt("id");
                    
            customer = new User(emailCustomer);
                    
            appResult = new Appointment(id, customer, barber, status, feedback, date, time);
            appsResult.add(appResult);

        }

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        conn.close();
        } catch (SQLException se) {

            // Loop through the SQL Exceptions
            while (se != null) {
                
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return appsResult;    
        
            
    }
    
    // get slots which are available, it means available field is true
    public List<Slot> getSlotsAvailable(User barber) {
        
        LocalTime time;  
        int id;
        Boolean available;
        Slot slot;
        List<Slot> slotsResult = new ArrayList<>();
       
        
         String query = "SELECT * FROM slot WHERE service_provider = '" + barber.getEmail() + "';";
        
               
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            //
            slotsResult = new ArrayList<>();
            
            // Loop through the result set
            while (rs.next()) {
                
                id = rs.getInt("id");
                time = rs.getTime("time").toLocalTime();
                available = rs.getBoolean("available");
                
                if (available) // if available free is true, this record will be returned
                    slotsResult.add(new Slot(id, barber, time, available));
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return slotsResult;
                
    }
    
    // get appoinments from a customer
    public List<Appointment> getAppoinmentsByCustomer(User customer) {
                        
        String query = "SELECT * FROM appointment WHERE customer = '" + customer.getEmail() + "';";
        String emailBarber, status, feedback;
        int id;
        LocalTime time;
        LocalDate date;
        User barber;
        List<Appointment> appsResult = new ArrayList<>();
        Appointment appResult = null;

        try {

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            while (rs.next()) {

            emailBarber = rs.getString("service_provider");
            status = rs.getString("status");
            feedback = rs.getString("feedback");
            time = rs.getTime("time").toLocalTime();
            date = rs.getDate("date").toLocalDate();
            id = rs.getInt("id");
                    
            barber = new User(emailBarber);
                    
            appResult = new Appointment(id, customer, barber, status, feedback, date, time);
            appsResult.add(appResult);

        }

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        conn.close();
        } catch (SQLException se) {

            // Loop through the SQL Exceptions
            while (se != null) {
                
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return appsResult;    
        
            
    }
        
    // get apppointments in a range of dates plus a email from barber    
    public List<Appointment> getAppoinmentsByBarberAndDates(User barber, LocalDate startDate, LocalDate endDate) {
                        
        String query = "SELECT * FROM appointment WHERE service_provider = '" + barber.getEmail() + 
                       "' AND date BETWEEN '" + startDate + "' AND '" + endDate + "' ORDER BY date ASC, time ASC;";
        String emailCustomer, status, feedback;
        User customer;
        int id;
        LocalTime time;
        LocalDate date;
        List<Appointment> appsResult = new ArrayList<>();
        Appointment appResult = null;

        try {

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            while (rs.next()) {

            emailCustomer = rs.getString("customer");
            status = rs.getString("status");
            feedback = rs.getString("feedback");
            time = rs.getTime("time").toLocalTime();
            date = rs.getDate("date").toLocalDate();
            id = rs.getInt("id");
                    
            customer = new User(emailCustomer);
                    
            appResult = new Appointment(id, customer, barber, status, feedback, date, time);
            appsResult.add(appResult);

        }

        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
        conn.close();
        } catch (SQLException se) {

            // Loop through the SQL Exceptions
            while (se != null) {
                
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return appsResult;    
           
    }   
    
    // get a list of users by filtering by a list of emails 
    public List<User> getUsers(List<String> emails) {
      
        String query = "SELECT * FROM user WHERE email IN ('";
        
        int index = 1;
        // create query
        for(String email: emails) {
            
            query += email;
            
            if (index == emails.size()) {
                
                query +=  "');";
                break;
                
            } else {
                
                query += "','";
                
            }
            index += 1;
            
        }
              
        String firstName, lastName, phoneNumber, type, passwordUser, location, email;
        List<User> usersResult = null;
        
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            //
            usersResult = new ArrayList<>();
            
            // Loop through the result set
            while (rs.next()) {
                
                
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                phoneNumber = rs.getString("phone_number");
                type = rs.getString("type");
                email = rs.getString("email");
                location = rs.getString("location");
                        
                usersResult.add(new User(email, firstName, lastName, phoneNumber, type, "", location));
                
            }

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return usersResult;
                
    }
    
    // create slost in the database for a barber
    public String createSlots(List<Slot> slots) {
        
        java.sql.Time time;   
        String sql = "INSERT INTO slot (service_provider, time, available) "
                         + "VALUES ('";
        
        int index = 1;
        // create query
        for (Slot slot: slots) {
        
            time = java.sql.Time.valueOf(slot.getTime());
            
            if (slot.getAvailable())
                sql = sql  + slot.getBarber().getEmail() + "','" + time + "','" + 1;
            else
                sql = sql  + slot.getBarber().getEmail() + "','" + time + "','" + 0;
                

            
            if (index == slots.size())
                sql = sql + "');";
            else
                sql = sql + "'),('";
            
        
            index += 1;
            
        }
        
        String resultMessage = null;
        
        try {
                    
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, user, password);
            
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sql);

            // Close the result set, statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Loop through the SQL Exceptions
            while (se != null) {
                resultMessage = "State  : " + se.getSQLState() +
                                "Message: " + se.getMessage() +
                                "Error  : " + se.getErrorCode() + "\n";

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
       
            return resultMessage;

        }         
        
    } 
}
