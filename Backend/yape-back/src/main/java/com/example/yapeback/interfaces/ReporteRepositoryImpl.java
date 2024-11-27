package com.example.yapeback.interfaces;

import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReporteRepositoryImpl implements ReporteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Ejecuta el procedimiento almacenado
    public void ejecutarReporteBusiness(int anio, String periodo) {
        String sql = "CALL reporte_business(?, ?)";
        jdbcTemplate.update(sql, anio, periodo);
    }

    // Método privado para obtener el rango de periodo basado en el tipo y número de periodo
    private Object[] getPeriodoRango(String tipoPeriodo, double numeroPeriodo) {
        double inicio = 0;
        double fin = 0;

        switch (tipoPeriodo.toLowerCase()) {
            case "mes":
                // Para el mes (1 a 12), solo retornamos el mes correspondiente
                inicio = numeroPeriodo;
                fin = numeroPeriodo;
                break;
            case "trimestre":
                // Para el trimestre (1, 2, 3, 4), mapea a los periodos respectivos
                inicio = 0.25 * (numeroPeriodo * 4 - 3);   // Primer mes del trimestre
                fin = 0.25 * (numeroPeriodo * 4);           // Último mes del trimestre
                break;
            case "semestre":
                // Para el semestre (1, 2), mapea a los periodos respectivos
                inicio = numeroPeriodo == 1 ? 1.0 : 2.0;  // Primer mes del semestre
                fin = numeroPeriodo == 1 ? 1.5 : 2.5;     // Último mes del semestre
                break;
            case "anio":
                // Para el año (1, 2, ...), mapea a todo el año
                inicio = 1.0;   // Primer mes del año
                fin = 12.0;     // Último mes del año
                break;
            default:
                throw new IllegalArgumentException("Tipo de periodo desconocido");
        }
        return new Object[]{inicio, fin};
    }


    // Consulta de tiempo promedio por etiqueta con filtro de rango en periodo
    public List<ReporteTiempoResolucionEtiqueta> obtenerTiempoResolucionEtiqueta(String tipoPeriodo, double numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, numeroPeriodo);
        String sql = "SELECT * FROM reporte_tiempo_resolucion_etiqueta WHERE periodo BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> {
            ReporteTiempoResolucionEtiqueta reporte = new ReporteTiempoResolucionEtiqueta();
            reporte.setCodEtiqueta(rs.getString("cod_etiqueta"));
            reporte.setTiempoPromedioResolucionHoras(rs.getDouble("tiempo_promedio_resolucion_horas"));
            double periodoDb = rs.getDouble("periodo");
            reporte.setPeriodo(String.valueOf(periodoDb)); // Transformar el período si es necesario
            return reporte;
        });
    }

    // Consulta de volumen general de tipificación con filtro de rango en periodo
    @Override
    public List<ReporteTipificacionGeneral> obtenerTipificacionGeneral(String tipoPeriodo, double numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, numeroPeriodo);
        String sql = "SELECT * FROM reporte_tipificacion_general WHERE periodo BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> {
            ReporteTipificacionGeneral reporte = new ReporteTipificacionGeneral();
            reporte.setVolumenTipificacion(rs.getInt("VolumenTipificacion"));
            reporte.setPeriodo(rs.getString("periodo"));
            return reporte;
        });
    }

    // Consulta de volumen detallado de tipificación con filtro de rango en periodo
    @Override
    public List<ReporteTipificacionDetallado> obtenerTipificacionDetallada(String tipoPeriodo, double numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, numeroPeriodo);
        String sql = "SELECT * FROM reporte_tipificacion_detallado WHERE periodo BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> {
            ReporteTipificacionDetallado reporte = new ReporteTipificacionDetallado();
            reporte.setCategoria(rs.getString("categoria"));
            reporte.setFuncionalidad(rs.getString("funcionalidad"));
            reporte.setMotivo(rs.getString("motivo"));
            reporte.setTipologia(rs.getString("tipologia"));
            reporte.setVolumenTipificacion(rs.getInt("VolumenTipificacion"));
            reporte.setPeriodo(rs.getString("periodo"));
            return reporte;
        });
    }

    // Consulta de clientes más activos por empresa con filtro de rango en periodo
    @Override
    public List<ReporteClientesActivos> obtenerClientesActivos(String tipoPeriodo, double numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, numeroPeriodo);
        String sql = "SELECT * FROM reporte_clientes_activos WHERE periodo BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> {
            ReporteClientesActivos reporte = new ReporteClientesActivos();
            reporte.setEmpresaCli(rs.getString("empresa_cli"));
            reporte.setClientesMasActivos(rs.getInt("ClientesMasActivos"));
            reporte.setPeriodo(rs.getString("periodo"));
            return reporte;
        });
    }

    // Consulta de volumen de tickets atendidos con filtro de rango en periodo
    @Override
    public List<ReporteVolumenTickets> obtenerVolumenTickets(String tipoPeriodo, double numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, numeroPeriodo);
        String sql = "SELECT * FROM reporte_volumen_tickets WHERE periodo BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> {
            ReporteVolumenTickets reporte = new ReporteVolumenTickets();
            reporte.setVolumenTicketsAtendidos(rs.getInt("VolumenTicketsAtendidos"));
            reporte.setPeriodo(rs.getString("periodo"));
            return reporte;
        });
    }

    // Consulta de tickets gestionados por empleado con filtro de rango en periodo
    @Override
    public List<ReporteTicketsEmpleado> obtenerTicketsPorEmpleado(String tipoPeriodo, double numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, numeroPeriodo);
        String sql = "SELECT * FROM reporte_tickets_empleado WHERE periodo BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> {
            ReporteTicketsEmpleado reporte = new ReporteTicketsEmpleado();
            reporte.setIdEmpleado(rs.getInt("ID_empleado"));
            reporte.setTicketsGestionados(rs.getInt("TicketsGestionados"));
            reporte.setPeriodo(rs.getString("periodo"));
            return reporte;
        });
    }

}
