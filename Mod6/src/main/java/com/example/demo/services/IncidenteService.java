package com.example.demo.services;


import com.example.demo.model.Incidente;
import com.example.demo.interfaces.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IncidenteService {

    @Autowired
    private IncidenteRepository incidenteRepository;

    // Crear un incidente
    public int crearIncidente(Incidente incidente) {
        return incidenteRepository.crearIncidente(incidente);
    }

    // Obtener todos los incidentes
    public List<Map<String, Object>> obtenerTodos() {
        return incidenteRepository.obtenerTodos();
    }

    // Obtener un incidente por ID
    public Map<String, Object> obtenerPorId(Long id) {
        return incidenteRepository.obtenerPorId(id);
    }

    // Eliminar un incidente por ID
    public int eliminarIncidente(Long id) {
        return incidenteRepository.eliminarIncidente(id);
    }

    // Actualizar un incidente
    public int actualizarIncidente(Long id, Incidente incidente) {
        return incidenteRepository.actualizarIncidente(id, incidente);
    }
}
