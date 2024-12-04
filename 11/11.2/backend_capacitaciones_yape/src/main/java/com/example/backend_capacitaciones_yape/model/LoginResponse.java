package com.example.backend_capacitaciones_yape.model;

public class LoginResponse {

    private String message;
    private String userId;  // Agregar el ID del usuario

    public LoginResponse(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }

    // Getters y setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}