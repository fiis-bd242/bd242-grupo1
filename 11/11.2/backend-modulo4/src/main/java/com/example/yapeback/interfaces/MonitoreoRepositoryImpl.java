package com.example.yapeback.interfaces;

import com.example.yapeback.model.Monitoreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MonitoreoRepositoryImpl implements MonitoreoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper para mapear las filas de la base de datos al objeto Monitoreo
    private RowMapper<Monitoreo> monitoreoRowMapper = new RowMapper<Monitoreo>() {
        @Override
        public Monitoreo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Monitoreo monitoreo = new Monitoreo();
            monitoreo.setCodEtiqueta(rs.getString("cod_etiqueta"));
            monitoreo.setFechaAccion(rs.getTimestamp("fecha").toLocalDateTime());
            monitoreo.setResponsable(rs.getString("responsable"));
            monitoreo.setComentario(rs.getString("comentario"));
            monitoreo.setTipoAccion(rs.getString("tipo_accion"));
            monitoreo.setNombreEstado(rs.getString("nombre_estado"));
            return monitoreo;
        }
    };

    @Override
    public String obtenerUltimoCodEtiqueta() {
        String sql = "SELECT ha.cod_etiqueta FROM historial_asignacion ha ORDER BY ha.fecha_accion DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @Override
    public List<Monitoreo> obtenerHistorialMonitoreo() {
        String sql = """
            SELECT ha.cod_etiqueta, ha.fecha_accion AS fecha, ha.realizado_por AS responsable, 
                   tat.comentario, ha.tipo_accion, eta.nombre_estado
            FROM historial_asignacion ha
            INNER JOIN ticket_asig_tip tat ON tat.cod_etiqueta = ha.cod_etiqueta
            INNER JOIN estado_ticket_asig eta ON eta.id_estado = tat.id_estado
            """;
        return jdbcTemplate.query(sql, monitoreoRowMapper);
    }
}
