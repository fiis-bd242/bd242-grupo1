package pe.edu.uni.yapenosql.repository;

import pe.edu.uni.yapenosql.model.*;

import java.util.List;

public interface ReporteRepository {
    List<TiempoResolucion> getResolutionTimeByLabel(int anio, String tipoPeriodo, int numeroPeriodo);
    List<VolumenGeneral> getGeneralVolume(int anio, String tipoPeriodo, int numeroPeriodo);
    List<TicketDetallado> getDetailedTickets(int anio, String tipoPeriodo, int numeroPeriodo);
    List<ClienteTicket> getTicketsByClientCompany(int anio, String tipoPeriodo, int numeroPeriodo);
    List<EmpleadoTicket> getTicketsByEmployee(int anio, String tipoPeriodo, int numeroPeriodo);
}
