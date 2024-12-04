package com.example.demo.controller;

import com.example.demo.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reportes")
public class ReporteIncidentesController {

    @Autowired
    private ReporteService reporteService;

    // Endpoint para generar el reporte de incidentes con porcentaje y grado de incidencia
    @GetMapping("/categorias")
    public List<Map<String, Object>> obtenerReporteDetallado() {
        return reporteService.obtenerReporteDetallado();
    }
}
