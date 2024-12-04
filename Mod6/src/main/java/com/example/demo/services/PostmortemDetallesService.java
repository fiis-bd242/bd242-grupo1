package com.example.demo.services;

import com.example.demo.model.PostmortemDetalles;
import com.example.demo.interfaces.PostmortemDetallesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostmortemDetallesService {

    @Autowired
    private PostmortemDetallesRepository detallesRepository;

    public int crearPostmortemDetalle(PostmortemDetalles detalle) {
        return detallesRepository.crearPostmortemDetalle(detalle);
    }

    public List<Map<String, Object>> obtenerTodos() {
        return detallesRepository.obtenerTodos();
    }

    public Map<String, Object> obtenerPorId(Long id) {
        return detallesRepository.obtenerPorId(id);
    }
}
