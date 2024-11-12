package com.example.yapeback.service;

import com.example.yapeback.interfaces.ReporteRepository;
import com.example.yapeback.model.CantidadTipificaciones;
import com.example.yapeback.model.FrecuenciaModificaciones;
import com.example.yapeback.model.FrecuenciaReasignacion;
import com.example.yapeback.model.TiempoPromedioResolucion;
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
}

