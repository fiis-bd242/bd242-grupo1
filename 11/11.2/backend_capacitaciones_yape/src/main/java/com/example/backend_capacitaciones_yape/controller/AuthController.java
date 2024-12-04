package com.example.backend_capacitaciones_yape.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.backend_capacitaciones_yape.model.LoginRequest;
import com.example.backend_capacitaciones_yape.model.LoginResponse;
import com.example.backend_capacitaciones_yape.model.UserCredentials;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {

    // Lista simulada de credenciales de usuario (en memoria)
    private List<UserCredentials> validCredentials = new ArrayList<>();

    // Constructor que agrega algunas credenciales válidas
    public AuthController() {
        // Agregar usuarios con ID, username y password
        validCredentials.add(new UserCredentials("12", "admin", "password123"));
        validCredentials.add(new UserCredentials("2", "PedroVD", "password456"));
        validCredentials.add(new UserCredentials("3", "ChromaC", "password789"));
        validCredentials.add(new UserCredentials("4", "BillF", "password101"));
        validCredentials.add(new UserCredentials("5", "RaulRR", "wordpass"));
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Verificar si las credenciales proporcionadas están en la lista de usuarios válidos
        for (UserCredentials credentials : validCredentials) {
            if (credentials.getUsername().equals(loginRequest.getUsername()) &&
                credentials.getPassword().equals(loginRequest.getPassword())) {
                // Si las credenciales coinciden, regresamos el ID del usuario
                return ResponseEntity.ok(new LoginResponse("Login successful", credentials.getId()));
            }
        }
        // Si las credenciales no coinciden
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}