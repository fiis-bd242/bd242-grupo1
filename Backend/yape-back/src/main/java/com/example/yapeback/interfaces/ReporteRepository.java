package com.example.yapeback.interfaces;

import com.example.yapeback.model.*;

import java.util.List;

public interface ReporteRepository {
    List<FrecuenciaReasignacion> obtenerFrecuenciaReasignacion();
    List<TiempoPromedioResolucion> obtenerTiempoPromedioResolucion();
    List<CantidadTipificaciones> obtenerCantidadTipificaciones();
    List<FrecuenciaModificaciones> obtenerFrecuenciaModificaciones();
    List<ReporteBusiness> obtenerReporteBusiness(int anio);
}
