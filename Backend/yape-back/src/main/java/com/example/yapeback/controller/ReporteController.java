package com.example.yapeback.controller;

import com.example.yapeback.model.CantidadTipificaciones;
import com.example.yapeback.model.FrecuenciaModificaciones;
import com.example.yapeback.model.FrecuenciaReasignacion;
import com.example.yapeback.model.TiempoPromedioResolucion;
import com.example.yapeback.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/frecuencia-reasignacion")
    public List<FrecuenciaReasignacion> obtenerFrecuenciaReasignacion() {
        return reporteService.getFrecuenciaReasignacion();
    }

    @GetMapping("/tiempo-promedio-resolucion")
    public List<TiempoPromedioResolucion> obtenerTiempoPromedioResolucion() {
        return reporteService.getTiempoPromedioResolucion();
    }

    @GetMapping("/cantidad-tipificaciones")
    public List<CantidadTipificaciones> obtenerCantidadTipificaciones() {
        return reporteService.getCantidadTipificaciones();
    }

    @GetMapping("/frecuencia-modificaciones")
    public List<FrecuenciaModificaciones> obtenerFrecuenciaModificaciones() {
        return reporteService.getFrecuenciaModificaciones();
    }
}

