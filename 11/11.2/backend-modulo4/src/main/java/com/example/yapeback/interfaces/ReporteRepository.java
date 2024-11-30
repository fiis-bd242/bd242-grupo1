package com.example.yapeback.interfaces;

import com.example.yapeback.model.*;

import java.util.List;

public interface ReporteRepository {
    void ejecutarReporteBusiness(int anio, String periodo);
    List<ReporteTiempoResolucionEtiqueta> obtenerTiempoResolucionEtiqueta(String tipoPeriodo, double numeroPeriodo);
    List<ReporteTipificacionGeneral> obtenerTipificacionGeneral(String tipoPeriodo, double numeroPeriodo);
    List<ReporteTipificacionDetallado> obtenerTipificacionDetallada(String tipoPeriodo, double numeroPeriodo);
    List<ReporteClientesActivos> obtenerClientesActivos(String tipoPeriodo, double numeroPeriodo);
    List<ReporteVolumenTickets> obtenerVolumenTickets(String tipoPeriodo, double numeroPeriodo);
    List<ReporteTicketsEmpleado> obtenerTicketsPorEmpleado(String tipoPeriodo, double numeroPeriodo);
}
