package com.example.assignmentsecond.model;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String gender;
    private String country;
    private String dob;
    private String email;
    private String phone;
    private String image;

    public User(String name, String gender, String country, String dob, String email, String phone, String image) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }
}