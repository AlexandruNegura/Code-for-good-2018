package com.example.pemil.juliashouse.Models;

import java.util.List;

public class Carrer {
    private long id;
    private String username;
    private String password;
    private List<Sit> sits;
    private String userType;
    private Boolean isLateHome;

    public Carrer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sit> getSits() {
        return sits;
    }

    public void setSits(List<Sit> sits) {
        this.sits = sits;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getLateHome() {
        return isLateHome;
    }

    public void setLateHome(Boolean lateHome) {
        isLateHome = lateHome;
    }
}
