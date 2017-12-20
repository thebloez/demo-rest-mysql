package com.springboot.restmysql.rest;

/**
 * Created by thebloez on 21/12/17.
 */
public class Dummy {
    private int id;
    private String first_name, last_name, email, gender, ip_address;

    public String getIp_address() {
        return ip_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
