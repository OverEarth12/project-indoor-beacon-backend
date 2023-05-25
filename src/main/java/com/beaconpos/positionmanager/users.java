package com.beaconpos.positionmanager;

import jakarta.persistence.*;

@Entity
public class users {
    @Id
    private String user_id;
    private String name;
    private String role;

    public users() {
    }

    public users(String user_id, String name, String role) {
        this.user_id = user_id;
        this.name = name;
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
