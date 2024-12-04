package pe.edu.uni.yapenosql.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.yapenosql.model.*;
import pe.edu.uni.yapenosql.repository.ReporteRepository;

import java.util.List;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    public List<TiempoResolucion> getResolutionTimeByLabel(int anio, String tipoPeriodo, int numeroPeriodo) {
        return reporteRepository.getResolutionTimeByLabel(anio,tipoPeriodo,numeroPeriodo);
    }

    public List<VolumenGeneral> getGeneralVolume(int anio, String tipoPeriodo, int numeroPeriodo) {
        return reporteRepository.getGeneralVolume(anio,tipoPeriodo,numeroPeriodo);
    }

    public List<TicketDetallado> getDetailedTickets(int anio, String tipoPeriodo, int numeroPeriodo) {
        return reporteRepository.getDetailedTickets(anio,tipoPeriodo,numeroPeriodo);
    }

    public List<ClienteTicket> getTicketsByClientCompany(int anio, String tipoPeriodo, int numeroPeriodo) {
        return reporteRepository.getTicketsByClientCompany(anio,tipoPeriodo,numeroPeriodo);
    }

    public List<EmpleadoTicket> getTicketsByEmployee(int anio, String tipoPeriodo, int numeroPeriodo) {
        return reporteRepository.getTicketsByEmployee(anio,tipoPeriodo,numeroPeriodo);
    }
}
