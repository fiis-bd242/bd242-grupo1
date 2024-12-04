package pe.edu.uni.yapenosql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.yapenosql.model.*;
import pe.edu.uni.yapenosql.service.ReporteService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class reporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/tiempo-resolucion")
    public List<TiempoResolucion> getResolutionTimeByLabel(
            @RequestParam int anio,
            @RequestParam String tipoPeriodo,
            @RequestParam int numeroPeriodo) {
        return reporteService.getResolutionTimeByLabel(anio, tipoPeriodo, numeroPeriodo);
    }

    @GetMapping("/volumen-general")
    public List<VolumenGeneral> getGeneralVolume(
            @RequestParam int anio,
            @RequestParam String tipoPeriodo,
            @RequestParam int numeroPeriodo) {
        return reporteService.getGeneralVolume(anio, tipoPeriodo, numeroPeriodo);
    }

    @GetMapping("/tickets-detallado")
    public List<TicketDetallado> getDetailedTickets(
            @RequestParam int anio,
            @RequestParam String tipoPeriodo,
            @RequestParam int numeroPeriodo) {
        return reporteService.getDetailedTickets(anio, tipoPeriodo, numeroPeriodo);
    }

    @GetMapping("/tickets-cliente")
    public List<ClienteTicket> getTicketsByClientCompany(
            @RequestParam int anio,
            @RequestParam String tipoPeriodo,
            @RequestParam int numeroPeriodo) {
        return reporteService.getTicketsByClientCompany(anio, tipoPeriodo, numeroPeriodo);
    }

    @GetMapping("/tickets-empleado")
    public List<EmpleadoTicket> getTicketsByEmployee(
            @RequestParam int anio,
            @RequestParam String tipoPeriodo,
            @RequestParam int numeroPeriodo) {
        return reporteService.getTicketsByEmployee(anio, tipoPeriodo, numeroPeriodo);
    }
}
