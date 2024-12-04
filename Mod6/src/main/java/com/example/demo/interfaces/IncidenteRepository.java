package com.example.demo.interfaces;

import com.example.demo.model.Incidente;

import java.util.List;
import java.util.Map;

public interface IncidenteRepository {
    
    // Crear un incidente
    int crearIncidente(Incidente incidente);

    // Obtener todos los incidentes
    List<Map<String, Object>> obtenerTodos();

    // Obtener un incidente por ID
    Map<String, Object> obtenerPorId(Long id);

    // Eliminar un incidente por ID
    int eliminarIncidente(Long id);

    // Actualizar un incidente
    int actualizarIncidente(Long id, Incidente incidente);
}
