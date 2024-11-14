package com.example.yapeback.service;

import com.example.yapeback.interfaces.ReporteRepository;
import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<FrecuenciaReasignacion> getFrecuenciaReasignacion() {
        return reporteRepository.obtenerFrecuenciaReasignacion();
    }

    public List<TiempoPromedioResolucion> getTiempoPromedioResolucion() {
        return reporteRepository.obtenerTiempoPromedioResolucion();
    }

    public List<CantidadTipificaciones> getCantidadTipificaciones() {
        return reporteRepository.obtenerCantidadTipificaciones();
    }

    public List<FrecuenciaModificaciones> getFrecuenciaModificaciones() {
        return reporteRepository.obtenerFrecuenciaModificaciones();
    }
    //Reporte analista
    // Método para obtener el reporte de negocio por año
    public List<ReporteBusiness> obtenerReporteBusiness(int anio) {
        return reporteRepository.obtenerReporteBusiness(anio);
    }

    // Método para obtener el reporte de analista de sugerencias y correcciones por año
    public List<ReporteAnalistaSugerenciasCorrecciones> obtenerReporteSugerenciasCorrecciones(int anio) {
        return reporteRepository.obtenerReporteSugerenciasCorrecciones(anio);
    }

    // Método para obtener el reporte de tasa de adopción por analista por año
    public List<ReporteAnalistaTasaAdopcion> obtenerReporteTasaAdopcion(int anio) {
        return reporteRepository.obtenerReporteTasaAdopcion(anio);
    }
}

