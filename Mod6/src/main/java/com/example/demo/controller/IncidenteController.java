package com.example.demo.controller;

import com.example.demo.model.Incidente;
import com.example.demo.services.IncidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    @Autowired
    private IncidenteService incidenteService;

    // Crear un incidente
    
    @PostMapping("/crear-incidente")
    public String crearIncidente(@RequestBody Incidente incidente) {
        int resultado = incidenteService.crearIncidente(incidente);
        return resultado == 1 ? "Incidente registrado con éxito" : "Error al registrar el incidente";
    }

    // Obtener todos los incidentes
    @GetMapping("/obtener-incidentes")
    public List<Map<String, Object>> obtenerTodos() {
        return incidenteService.obtenerTodos();
    }

    // Obtener incidente por ID
    @GetMapping("/obtener-incidente/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Long id) {
        return incidenteService.obtenerPorId(id);
    }

    //Eliminar incidente por ID
    @DeleteMapping("/eliminar-incidente/{id}")
    public String eliminarIncidente(@PathVariable Long id) {
        int resultado = incidenteService.eliminarIncidente(id);
        return resultado == 1 ? "Incidente eliminado con éxito" : "Error al eliminar el incidente";
    }

    // Actualizar un incidente
    
    @PutMapping("/actualizar-incidente/{id}")
    public String actualizarIncidente(@PathVariable Long id, @RequestBody Incidente incidente) {
        int resultado = incidenteService.actualizarIncidente(id, incidente);
        return resultado == 1 ? "Incidente actualizado con éxito" : "Error al actualizar el incidente";
    }
}
