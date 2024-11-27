package com.example.yapeback.interfaces;

import com.example.yapeback.model.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class PermisoRepositoryImpl implements PermisoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeador para Permiso
    private static class PermisoRowMapper implements RowMapper<Permiso> {
        @Override
        public Permiso mapRow(ResultSet rs, int rowNum) throws SQLException {
            Permiso permiso = new Permiso();
            permiso.setIdPermiso(rs.getLong("id_permiso"));
            permiso.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            permiso.setFechaFinal(rs.getDate("fecha_final").toLocalDate());
            permiso.setEstado(Permiso.EstadoPermiso.valueOf(rs.getString("estado"))); // Enum mapping
            permiso.setComentario(rs.getString("comentario"));
            permiso.setComentarioAdmin(rs.getString("comentario_admin"));
            permiso.setIdEmpleado(rs.getLong("id_empleado"));
            permiso.setIdTipoPermiso(rs.getLong("id_tipo_permiso"));
            permiso.setFechaCambioEstado(rs.getTimestamp("fecha_cambio_estado").toLocalDateTime());
            return permiso;
        }
    }
    
    @Override
    public Permiso save(Permiso permiso) {
        String sql = """
            INSERT INTO Permiso (fecha_inicio, fecha_final, estado, comentario, ID_empleado, ID_tipo_permiso)
            VALUES (?, ?, CAST(? AS estado_permiso_enum), ?, ?, ?)
            RETURNING id_permiso
        """;
        
        Long idPermiso = jdbcTemplate.queryForObject(
            sql,
            new Object[]{
                permiso.getFechaInicio(),
                permiso.getFechaFinal(),
                permiso.getEstado().name(), // Convierte el Enum a String compatible
                permiso.getComentario(),
                permiso.getIdEmpleado(),
                permiso.getIdTipoPermiso()
            },
            Long.class // Tipo del ID que estás recuperando
        );
        
        permiso.setIdPermiso(idPermiso); // Asigna el ID generado al objeto permiso
        return permiso;
    }
    
    @Override
    public List<Map<String, Object>> findByEmpleado(Long idEmpleado) {
        String sql = """
            SELECT p.ID_permiso, tp.nombre AS tipo_permiso, p.fecha_inicio, p.fecha_final, p.estado, 
                   p.comentario, p.comentario_admin, p.fecha_cambio_estado
            FROM Permiso p
            JOIN Tipo_permiso tp ON p.ID_tipo_permiso = tp.ID_tipo_permiso
            WHERE p.ID_empleado = ?
            ORDER BY 
                CASE WHEN p.estado = 'Pendiente' THEN 0 ELSE 1 END, 
                p.fecha_cambio_estado DESC NULLS LAST
        """;
        return jdbcTemplate.queryForList(sql, idEmpleado);
    }

    @Override
    public List<Map<String, Object>> findAllWithFilters(String estado, String tipoPermiso) {
        StringBuilder sql = new StringBuilder("""
            SELECT p.ID_permiso, e.Nombre, e.Apellido, p.fecha_inicio, p.fecha_final, p.estado,
                   tp.nombre AS tipo_permiso, p.comentario, p.comentario_admin
            FROM Permiso p
            JOIN Empleado e ON p.ID_empleado = e.ID_empleado
            JOIN Tipo_permiso tp ON p.ID_tipo_permiso = tp.ID_tipo_permiso
            WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        if (estado != null && !estado.isEmpty()) {
            sql.append(" AND p.estado = ?::estado_permiso_enum"); // Conversión explícita
            params.add(estado);
        }

        if (tipoPermiso != null && !tipoPermiso.isEmpty()) {
            sql.append(" AND tp.nombre = ?::tipo_permiso_enum");
            params.add(tipoPermiso);
        }
        
     // Ordenar pendientes primero
        sql.append("""
            ORDER BY 
                CASE WHEN p.estado = 'Pendiente' THEN 0 ELSE 1 END, 
                p.fecha_cambio_estado DESC NULLS LAST
        """);

        return jdbcTemplate.queryForList(sql.toString(), params.toArray());
    }

    private static final Logger log = LoggerFactory.getLogger(PermisoRepositoryImpl.class);

    @Override
    public void updateEstado(Long idPermiso, String nuevoEstado, String comentarioAdmin) {
        log.info("Actualizando permiso con ID: {}, nuevoEstado: {}, comentarioAdmin: {}", idPermiso, nuevoEstado, comentarioAdmin);
        
        String sql = """
            UPDATE Permiso
            SET estado = CAST(? AS estado_permiso_enum), comentario_admin = ?, fecha_cambio_estado = CURRENT_TIMESTAMP
            WHERE ID_permiso = ?
        """;
        jdbcTemplate.update(sql, nuevoEstado, comentarioAdmin, idPermiso);
    }
    
    @Override
    public Map<String, Object> findDetallePermiso(Long idPermiso) {
        String sql = """
            SELECT 
                p.ID_permiso, p.fecha_inicio, p.fecha_final, p.estado, 
                tp.nombre AS tipo_permiso, 
                p.comentario AS comentario_empleado, 
                p.comentario_admin AS comentario_administrador,
                e.Nombre AS empleado_nombre, e.Apellido AS empleado_apellido, 
                pu.nombre AS puesto, d.descripcion AS departamento, 
                e.Telefono, 
                ep.nombre_archivo, ep.ruta_archivo
            FROM Permiso p
            JOIN Empleado e ON p.ID_empleado = e.ID_empleado
            JOIN Tipo_permiso tp ON p.ID_tipo_permiso = tp.ID_tipo_permiso
            JOIN Puesto pu ON e.id_puesto = pu.id_puesto
            JOIN Departamento d ON pu.id_departamento = d.id_departamento
            LEFT JOIN EvidenciaPermiso ep ON p.ID_permiso = ep.ID_permiso
            WHERE p.ID_permiso = ?
        """;

        return jdbcTemplate.queryForMap(sql, idPermiso);
    }

}
