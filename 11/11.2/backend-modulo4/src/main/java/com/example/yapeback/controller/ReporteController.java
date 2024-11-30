package com.example.yapeback.controller;

import com.example.yapeback.service.ReporteService;
import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteBusinessService;

    @PostMapping("/business/generar")
    public void generarReporteBusiness(
            @RequestParam("anio") int anio,
            @RequestParam("periodo") String periodo) {
        reporteBusinessService.ejecutarReporteBusiness(anio, periodo);
    }

    @GetMapping("/business/tiempo-resolucion")
    public List<ReporteTiempoResolucionEtiqueta> obtenerTiempoResolucionEtiqueta(
            @RequestParam String tipoPeriodo,
            @RequestParam double numeroPeriodo){
        return reporteBusinessService.obtenerTiempoResolucionEtiqueta(tipoPeriodo,numeroPeriodo);
    }

    @GetMapping("/business/tipificacion-general")
    public List<ReporteTipificacionGeneral> obtenerTipificacionGeneral(
            @RequestParam String tipoPeriodo,
            @RequestParam double numeroPeriodo) {
        return reporteBusinessService.obtenerTipificacionGeneral(tipoPeriodo,numeroPeriodo);
    }

    @GetMapping("/business/tipificacion-detallada")
    public List<ReporteTipificacionDetallado> obtenerTipificacionDetallada(
            @RequestParam String tipoPeriodo,
            @RequestParam double numeroPeriodo) {
        return reporteBusinessService.obtenerTipificacionDetallada(tipoPeriodo,numeroPeriodo);
    }

    @GetMapping("/business/volumen-tickets")
    public List<ReporteVolumenTickets> obtenerVolumenTickets(
            @RequestParam String tipoPeriodo,
            @RequestParam double numeroPeriodo) {
        return reporteBusinessService.obtenerVolumenTickets(tipoPeriodo,numeroPeriodo);
    }

    @GetMapping("/business/clientes-activos")
    public List<ReporteClientesActivos> obtenerClientesActivos(
            @RequestParam String tipoPeriodo,
            @RequestParam double numeroPeriodo) {
        return reporteBusinessService.obtenerClientesActivos(tipoPeriodo,numeroPeriodo);
    }

    @GetMapping("/business/tickets-empleado")
    public List<ReporteTicketsEmpleado> obtenerTicketsPorEmpleado(
            @RequestParam String tipoPeriodo,
            @RequestParam double numeroPeriodo) {
        return reporteBusinessService.obtenerTicketsPorEmpleado(tipoPeriodo,numeroPeriodo);
    }
}
