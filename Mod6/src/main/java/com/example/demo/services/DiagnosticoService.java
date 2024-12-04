package com.example.demo.services;

import com.example.demo.model.Diagnostico;
import com.example.demo.interfaces.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    // Crear un diagnóstico
    public int crearDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.crearDiagnostico(diagnostico);
    }
}
