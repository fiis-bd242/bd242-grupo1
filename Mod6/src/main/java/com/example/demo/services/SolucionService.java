package com.example.demo.services;

import com.example.demo.model.Solucion;
import com.example.demo.interfaces.SolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolucionService {

    @Autowired
    private SolucionRepository solucionRepository;

    // Crear una solución
    public int crearSolucion(Solucion solucion) {
        return solucionRepository.crearSolucion(solucion);
    }
}
