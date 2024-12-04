package com.example.backend_capacitaciones_yape.model;

public class UserCredentials {
    private String id;
    private String username;
    private String password;

    // Constructor
    public UserCredentials(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
