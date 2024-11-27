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

    public void ejecutarReporteBusiness(int anio, String periodo) {
        // Ejecutamos el reporte de negocio
        reporteRepository.ejecutarReporteBusiness(anio, periodo);
    }

    public List<ReporteTiempoResolucionEtiqueta> obtenerTiempoResolucionEtiqueta(String periodo,double numeroPeriodo) {
        return reporteRepository.obtenerTiempoResolucionEtiqueta(periodo,numeroPeriodo);
    }

    public List<ReporteTipificacionGeneral> obtenerTipificacionGeneral(String periodo,double numeroPeriodo) {
        return reporteRepository.obtenerTipificacionGeneral(periodo,numeroPeriodo);
    }

    public List<ReporteTipificacionDetallado> obtenerTipificacionDetallada(String periodo,double numeroPeriodo) {
        return reporteRepository.obtenerTipificacionDetallada(periodo,numeroPeriodo);
    }


    public List<ReporteVolumenTickets> obtenerVolumenTickets(String periodo,double numeroPeriodo) {
        return reporteRepository.obtenerVolumenTickets(periodo,numeroPeriodo);
    }

    public List<ReporteClientesActivos> obtenerClientesActivos(String periodo,double numeroPeriodo) {
        return reporteRepository.obtenerClientesActivos(periodo,numeroPeriodo);
    }

    public List<ReporteTicketsEmpleado> obtenerTicketsPorEmpleado(String periodo,double numeroPeriodo) {
        return reporteRepository.obtenerTicketsPorEmpleado(periodo,numeroPeriodo);
    }
}
