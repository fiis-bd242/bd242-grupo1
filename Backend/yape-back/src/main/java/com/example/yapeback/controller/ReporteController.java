package com.example.yapeback.controller;

import com.example.yapeback.model.*;
import com.example.yapeback.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/business/frecuencia-reasignacion")
    public List<FrecuenciaReasignacion> obtenerFrecuenciaReasignacion() {
        return reporteService.getFrecuenciaReasignacion();
    }

    @GetMapping("/business/tiempo-promedio-resolucion")
    public List<TiempoPromedioResolucion> obtenerTiempoPromedioResolucion() {
        return reporteService.getTiempoPromedioResolucion();
    }

    @GetMapping("/business/cantidad-tipificaciones")
    public List<CantidadTipificaciones> obtenerCantidadTipificaciones() {
        return reporteService.getCantidadTipificaciones();
    }

    @GetMapping("/business/frecuencia-modificaciones")
    public List<FrecuenciaModificaciones> obtenerFrecuenciaModificaciones() {
        return reporteService.getFrecuenciaModificaciones();
    }
    // Endpoint para obtener el reporte de negocio por año
    @GetMapping("/analista/reporte-business/{anio}")
    public List<ReporteBusiness> obtenerReporteBusiness(@PathVariable int anio) {
        return reporteService.obtenerReporteBusiness(anio);
    }

    // Endpoint para obtener el reporte de sugerencias y correcciones por año
    @GetMapping("/analista/reporte-sugerencias-correcciones/{anio}")
    public List<ReporteAnalistaSugerenciasCorrecciones> obtenerReporteSugerenciasCorrecciones(@PathVariable int anio) {
        return reporteService.obtenerReporteSugerenciasCorrecciones(anio);
    }

    // Endpoint para obtener el reporte de tasa de adopción por analista por año
    @GetMapping("/analista/reporte-tasa-adopcion/{anio}")
    public List<ReporteAnalistaTasaAdopcion> obtenerReporteTasaAdopcion(@PathVariable int anio) {
        return reporteService.obtenerReporteTasaAdopcion(anio);
    }
}

