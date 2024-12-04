package com.example.demo.services;

import com.example.demo.model.AnalisisPostmortem;
import com.example.demo.interfaces.AnalisisPostmortemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalisisPostmortemService {

    @Autowired
    private AnalisisPostmortemRepository analisisPostmortemRepository;

    public int crearAnalisisPostmortem(AnalisisPostmortem analisis) {
        return analisisPostmortemRepository.crearAnalisisPostmortem(analisis);
    }

    public List<Map<String, Object>> obtenerTodos() {
        return analisisPostmortemRepository.obtenerTodos();
    }

    public Map<String, Object> obtenerPorId(Long id) {
        return analisisPostmortemRepository.obtenerPorId(id);
    }
}
