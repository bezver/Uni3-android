package com.example.usermanager.Core.Models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private int iconKey;

    public User(int id, String firstname, String lastname, String email, String address, int iconKey) {
        this(firstname, lastname, email, address, iconKey);
        this.id = id;
    }

    public User(String firstname, String lastname, String email, String address, int iconKey) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.iconKey = iconKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIconKey() {
        return iconKey;
    }

    public void setIconKey(int iconKey) {
        this.iconKey = iconKey;
    }
}