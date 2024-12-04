package com.example.demo.interfaces;

import com.example.demo.model.AnalisisPostmortem;

import java.util.List;
import java.util.Map;

public interface AnalisisPostmortemRepository {
    int crearAnalisisPostmortem(AnalisisPostmortem analisis);
    List<Map<String, Object>> obtenerTodos();
    Map<String, Object> obtenerPorId(Long id);
}
