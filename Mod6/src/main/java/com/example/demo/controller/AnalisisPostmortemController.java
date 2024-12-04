package com.example.demo.controller;

import com.example.demo.model.AnalisisPostmortem;
import com.example.demo.services.AnalisisPostmortemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/analisisPostmortem")
public class AnalisisPostmortemController {

    @Autowired
    private AnalisisPostmortemService analisisPostmortemService;

    @PostMapping("/crear-analisis-post")
    public String crearAnalisis(@RequestBody AnalisisPostmortem analisis) {
        int resultado = analisisPostmortemService.crearAnalisisPostmortem(analisis);
        return resultado == 1 ? "Análisis postmortem registrado con éxito" : "Error al registrar el análisis postmortem";
    }

    @GetMapping("/obtener-analisis-post")
    public List<Map<String, Object>> obtenerTodos() {
        return analisisPostmortemService.obtenerTodos();
    }

    @GetMapping("/obtener-analisis-post/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Long id) {
        return analisisPostmortemService.obtenerPorId(id);
    }
}
