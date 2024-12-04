package com.example.demo.controller;

import com.example.demo.model.Solucion;
import com.example.demo.services.SolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/soluciones")
public class SolucionController {

    @Autowired
    private SolucionService solucionService;

    // Crear una solución
    @PostMapping("/crear-solu")
    public String crearSolucion(@RequestBody Solucion solucion) {
        int resultado = solucionService.crearSolucion(solucion);
        return resultado == 1 ? "Solución registrada con éxito" : "Error al registrar la solución";
    }
}
