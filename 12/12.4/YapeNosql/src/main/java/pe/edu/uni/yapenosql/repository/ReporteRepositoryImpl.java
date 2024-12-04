package pe.edu.uni.yapenosql.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.edu.uni.yapenosql.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReporteRepositoryImpl implements ReporteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TiempoResolucion mapRowToResolutionTimeByLabel(ResultSet rs) throws SQLException {
        TiempoResolucion stats = new TiempoResolucion();
        stats.setCodEtiqueta(rs.getLong("cod_etiqueta"));
        stats.setTiempoResolucionPromedio(rs.getDouble("tiempo_resolucion_promedio"));
        stats.setCategoria(rs.getString("categoria"));
        stats.setFuncionalidad(rs.getString("funcionalidad"));
        stats.setMotivo(rs.getString("motivo"));
        return stats;
    }

    private VolumenGeneral mapRowToGeneralVolume(ResultSet rs) throws SQLException {
        VolumenGeneral stats = new VolumenGeneral();
        stats.setVolumenTickets(rs.getLong("volumen_tickets"));
        stats.setPeriodo(rs.getString("periodo"));
        return stats;
    }

    private TicketDetallado mapRowToDetailedTickets(ResultSet rs) throws SQLException {
        TicketDetallado stats = new TicketDetallado();
        stats.setCategoria(rs.getString("categoria"));
        stats.setFuncionalidad(rs.getString("funcionalidad"));
        stats.setMotivo(rs.getString("motivo"));
        stats.setVolumenTickets(rs.getLong("volumen_tickets"));
        stats.setPeriodo(rs.getString("periodo"));
        return stats;
    }

    private ClienteTicket mapRowToTicketsByClientCompany(ResultSet rs) throws SQLException {
        ClienteTicket stats = new ClienteTicket();
        stats.setEmpresaCliente(rs.getString("empresa_cliente"));
        stats.setClientesActivos(rs.getLong("clientes_activos"));
        stats.setPeriodo(rs.getString("periodo"));
        return stats;
    }

    private EmpleadoTicket mapRowToTicketsByEmployee(ResultSet rs) throws SQLException {
        EmpleadoTicket stats = new EmpleadoTicket();
        stats.setNombreEmpleado(rs.getString("nombre_empleado"));
        stats.setVolumenTicketsGestionados(rs.getLong("volumen_tickets_gestionados"));
        stats.setPeriodo(rs.getString("periodo"));
        return stats;
    }

    @Override
    public List<TiempoResolucion> getResolutionTimeByLabel(int anio, String tipoPeriodo, int numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, anio, numeroPeriodo);
        String sql = """
        SELECT 
            d.cod_etiqueta, 
            AVG(f.tiempo_resolucion) AS tiempo_resolucion_promedio, 
            d.categoria, 
            d.funcionalidad, 
            d.motivo
        FROM Fact_Tickets f
        JOIN Dim_Etiqueta d ON f.id_etiqueta = d.cod_etiqueta
        WHERE f.fecha_inicio BETWEEN ? AND ? 
        GROUP BY d.cod_etiqueta, d.categoria, d.funcionalidad, d.motivo
        """;
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> mapRowToResolutionTimeByLabel(rs));
    }

    @Override
    public List<VolumenGeneral> getGeneralVolume(int anio, String tipoPeriodo, int numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, anio, numeroPeriodo);
        String sql = """
        SELECT 
            COUNT(f.cod_ticket) AS volumen_tickets, 
            dt.anio AS periodo
        FROM Fact_Tickets f
        JOIN Dim_Tiempo dt ON DATE(f.fecha_inicio) = dt.fecha
        WHERE f.fecha_inicio BETWEEN ? AND ?
        GROUP BY dt.anio
        """;
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> mapRowToGeneralVolume(rs));
    }

    @Override
    public List<TicketDetallado> getDetailedTickets(int anio, String tipoPeriodo, int numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, anio, numeroPeriodo);
        String sql = """
        SELECT 
            d.categoria, 
            d.funcionalidad, 
            d.motivo, 
            COUNT(f.cod_ticket) AS volumen_tickets, 
            dt.anio AS periodo
        FROM Fact_Tickets f
        JOIN Dim_Etiqueta d ON f.id_etiqueta = d.cod_etiqueta
        JOIN Dim_Tiempo dt ON DATE(f.fecha_inicio) = dt.fecha
        WHERE f.fecha_inicio BETWEEN ? AND ?
        GROUP BY d.categoria, d.funcionalidad, d.motivo, dt.anio
        """;
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> mapRowToDetailedTickets(rs));
    }

    @Override
    public List<ClienteTicket> getTicketsByClientCompany(int anio, String tipoPeriodo, int numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, anio, numeroPeriodo);
        String sql = """
        SELECT 
            c.empresa_cliente, 
            COUNT(DISTINCT f.id_cliente) AS clientes_activos, 
            dt.anio AS periodo
        FROM Fact_Tickets f
        JOIN Dim_Cliente c ON f.id_cliente = c.id_cliente
        JOIN Dim_Tiempo dt ON DATE(f.fecha_inicio) = dt.fecha
        WHERE f.fecha_inicio BETWEEN ? AND ?
        GROUP BY c.empresa_cliente, dt.anio
        """;
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> mapRowToTicketsByClientCompany(rs));
    }

    @Override
    public List<EmpleadoTicket> getTicketsByEmployee(int anio, String tipoPeriodo, int numeroPeriodo) {
        Object[] rangoPeriodo = getPeriodoRango(tipoPeriodo, anio, numeroPeriodo);
        String sql = """
        SELECT 
            e.nombre_empleado, 
            COUNT(f.cod_ticket) AS volumen_tickets_gestionados, 
            dt.anio AS periodo
        FROM Fact_Tickets f
        JOIN Dim_Empleado e ON f.id_empleado = e.id_empleado
        JOIN Dim_Tiempo dt ON DATE(f.fecha_inicio) = dt.fecha
        WHERE f.fecha_inicio BETWEEN ? AND ?
        GROUP BY e.nombre_empleado, dt.anio
        """;
        return jdbcTemplate.query(sql, rangoPeriodo, (rs, rowNum) -> mapRowToTicketsByEmployee(rs));
    }

    private Object[] getPeriodoRango(String tipoPeriodo, int anio, Integer numeroPeriodo) {
        int inicioMes = 0;
        int finMes = 0;

        if ("anio".equalsIgnoreCase(tipoPeriodo)) {
            return new Object[]{anio + "-01-01", anio + "-12-31"};
        }

        switch (tipoPeriodo.toLowerCase()) {
            case "mes":
                inicioMes = numeroPeriodo;
                finMes = numeroPeriodo;
                break;
            case "trimestre":
                inicioMes = (numeroPeriodo - 1) * 3 + 1;
                finMes = numeroPeriodo * 3;
                break;
            case "semestre":
                inicioMes = (numeroPeriodo - 1) * 6 + 1;
                finMes = numeroPeriodo * 6;
                break;
        }
        String inicio = anio + "-" + (inicioMes < 10 ? "0" + inicioMes : inicioMes) + "-01";
        String fin = anio + "-" + (finMes < 10 ? "0" + finMes : finMes) + "-31";
        return new Object[]{inicio, fin};
    }
}
