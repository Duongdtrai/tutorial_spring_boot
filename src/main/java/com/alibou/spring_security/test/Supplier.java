package com.alibou.spring_security.test;

import java.util.List;

public class Supplier {

    // Các thuộc tính mới của Supplier
    private int id;
    private String fullname;
    private String phoneNumber;
    private String address;
    private String email;
    private String description;

    private static Supplier instance = new Supplier();

    
    private Supplier() {
        this.id = 0;
        this.fullname = "PTD";
        this.phoneNumber = "Default Phone Number";
        this.address = "Default Address";
        this.email = "Default Email";
        this.description = "Default Description";
    }

    public static Supplier getInstance() {
        return instance;
    }

    // Getter và setter cho các thuộc tính của Supplier
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // method CRUD
    public List<Supplier> searchSupplierByName(String name) {
        return null;
    }

    public void create(Supplier supplier) {
        // handle with database
    }

    public void update(Supplier supplier) {
        // handle with database
    }

    public void delete(Supplier supplier) {
        // handle with database
    }


}
