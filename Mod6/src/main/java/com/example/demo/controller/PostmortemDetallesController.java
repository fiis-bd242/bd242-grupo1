package com.example.demo.controller;

import com.example.demo.model.PostmortemDetalles;
import com.example.demo.services.PostmortemDetallesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/postmortemDetalles")
public class PostmortemDetallesController {

    @Autowired
    private PostmortemDetallesService detallesService;

    @PostMapping("/crear-postmortem")
    public String crearDetalle(@RequestBody PostmortemDetalles detalle) {
        int resultado = detallesService.crearPostmortemDetalle(detalle);
        return resultado == 1 ? "Detalle postmortem registrado con éxito" : "Error al registrar el detalle postmortem";
    }

    @GetMapping("/obtener-postmortem")
    public List<Map<String, Object>> obtenerTodos() {
        return detallesService.obtenerTodos();
    }

    @GetMapping("/obtener-postmortem/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Long id) {
        return detallesService.obtenerPorId(id);
    }
}
