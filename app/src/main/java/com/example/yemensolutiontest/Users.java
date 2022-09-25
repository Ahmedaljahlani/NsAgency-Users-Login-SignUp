package com.example.yemensolutiontest;

public class Users {

    String name;
    String email;
    int phone;
    String location;
    int id;

    public Users(String name, String email, int phone, String location, int id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.id = id;
    }

    public Users(String name, String email, int phone, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}