package modals;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String emailaddress;
    private String phoneNumber;
    private String address;
    private String date;



    private HashMap<String, Integer> requests = new HashMap<String, Integer>();



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User(Long id, String fullName, String username, String password, String emailaddress, String phoneNumber, String address, String date) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.emailaddress = emailaddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Integer> getRequests() {
        return requests;
    }

    public void addRequests(String name,int value) {
        requests.put(name,value);
    }

}
