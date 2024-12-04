package com.example.demo.interfaces;

import com.example.demo.model.PostmortemDetalles;

import java.util.List;
import java.util.Map;

public interface PostmortemDetallesRepository {
    int crearPostmortemDetalle(PostmortemDetalles detalle);

    List<Map<String, Object>> obtenerTodos();

    Map<String, Object> obtenerPorId(Long id);
}
