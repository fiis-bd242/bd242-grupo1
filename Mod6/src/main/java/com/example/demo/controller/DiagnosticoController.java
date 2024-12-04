package com.example.demo.controller;

import com.example.demo.model.Diagnostico;
import com.example.demo.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    // Crear un diagnóstico
    @PostMapping("/crear")
    public String crearDiagnostico(@RequestBody Diagnostico diagnostico) {
        int resultado = diagnosticoService.crearDiagnostico(diagnostico);
        return resultado == 1 ? "Diagnóstico registrado con éxito" : "Error al registrar el diagnóstico";
    }
}

