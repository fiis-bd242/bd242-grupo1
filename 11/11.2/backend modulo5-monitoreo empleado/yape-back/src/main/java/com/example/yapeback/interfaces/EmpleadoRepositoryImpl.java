package com.example.yapeback.interfaces;

import com.example.yapeback.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeador para la lista básica de empleados
    private static final class EmpleadoBasicRowMapper implements RowMapper<Map<String, Object>> {
        @Override
        public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            Map<String, Object> empleado = new HashMap<>();
            empleado.put("id_empleado", rs.getLong("id_empleado"));
            empleado.put("nombre", rs.getString("nombre"));
            empleado.put("apellido", rs.getString("apellido"));
            empleado.put("fecha_nacimiento", rs.getDate("fecha_nacimiento").toLocalDate());
            empleado.put("fecha_ingreso", rs.getDate("fecha_ingreso").toLocalDate());
            empleado.put("estado", rs.getString("estado"));
            empleado.put("documento_identidad", rs.getString("documento_identidad"));
            empleado.put("telefono", rs.getString("telefono"));
            empleado.put("puesto", rs.getString("puesto"));
            empleado.put("departamento", rs.getString("departamento"));
            return empleado;
        }
    }

    // Mapeador para obtener objetos Empleado
    private static final class EmpleadoRowMapper implements RowMapper<Empleado> {
        @Override
        public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
            Empleado empleado = new Empleado();
            empleado.setId_empleado(rs.getLong("id_empleado"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setApellido(rs.getString("apellido"));
            java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            if (fechaNacimiento != null) {
                empleado.setFecha_nacimiento(fechaNacimiento.toLocalDate());
            }
            java.sql.Date fechaIngreso = rs.getDate("fecha_ingreso");
            if (fechaIngreso != null) {
                empleado.setFecha_ingreso(fechaIngreso.toLocalDate());
            }
            empleado.setEstado(rs.getString("estado"));
            empleado.setDocumento_identidad(rs.getString("documento_identidad"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setId_puesto(rs.getLong("id_puesto"));
            return empleado;
        }
    }

    @Override
    public List<Map<String, Object>> findByFilters(Long idEmpleado, String nombre, String apellido, String puesto, String departamento, String estado) {
        StringBuilder sql = new StringBuilder(
            "SELECT e.ID_empleado, e.Nombre, e.Apellido, e.fecha_nacimiento, e.fecha_ingreso, e.Estado, " +
            "e.Documento_identidad, e.Telefono, p.nombre AS puesto, d.descripcion AS departamento " +
            "FROM Empleado e " +
            "JOIN Puesto p ON e.id_puesto = p.id_puesto " +
            "JOIN Departamento d ON p.id_departamento = d.id_departamento WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();
        if (idEmpleado != null) {
            sql.append("AND e.ID_empleado = ? ");
            params.add(idEmpleado);
        }
        if (nombre != null && !nombre.isEmpty()) {
            sql.append("AND e.Nombre LIKE ? ");
            params.add("%" + nombre + "%");
        }
        if (apellido != null && !apellido.isEmpty()) {
            sql.append("AND e.Apellido LIKE ? ");
            params.add("%" + apellido + "%");
        }
        if (puesto != null && !puesto.isEmpty()) {
            sql.append("AND p.nombre LIKE ? ");
            params.add("%" + puesto + "%");
        }
        if (departamento != null && !departamento.isEmpty()) {
            sql.append("AND d.descripcion LIKE ? ");
            params.add("%" + departamento + "%");
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append("AND e.Estado = ? ");
            params.add(estado);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new EmpleadoBasicRowMapper());
    }

    @Override
    public Map<String, Object> findDetailsById(Long idEmpleado) {
        String sql = """
            SELECT e.ID_empleado, e.Nombre, e.Apellido, 
                   DATE_PART('year', AGE(CURRENT_DATE, e.fecha_nacimiento)) AS edad,
                   e.Documento_identidad, e.fecha_nacimiento, e.Telefono, 
                   p.nombre AS puesto, p.paga AS salario, 
                   d.descripcion AS departamento,
                   e.Estado, e.fecha_ingreso
            FROM Empleado e
            JOIN Puesto p ON e.id_puesto = p.id_puesto
            JOIN Departamento d ON p.id_departamento = d.id_departamento
            WHERE e.ID_empleado = ?
        """;

        return jdbcTemplate.queryForMap(sql, idEmpleado);
    }
    /**
     * Recupera todos los registros básicos de la tabla empleado.
     * Útil para operaciones simples sin necesidad de datos relacionados.
     */
    @Override
    public List<Empleado> findAll() {
        String sql = "SELECT * FROM empleado";
        return jdbcTemplate.query(sql, new EmpleadoRowMapper());
    }
    /**
     * Busca un empleado por su ID (sin incluir relaciones con puesto o departamento).
     * Útil para tareas básicas donde no se necesita información detallada.
     */
    @Override
    public Empleado findById(Long id) {
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";
        return jdbcTemplate.queryForObject(sql, new EmpleadoRowMapper(), id);
    }

    @Override
    public Empleado save(Empleado empleado) {
        if (empleado.getId_empleado() == null) {
            String sql = "INSERT INTO empleado (nombre, apellido, fecha_nacimiento, fecha_ingreso, estado, documento_identidad, telefono, id_puesto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, empleado.getNombre(), empleado.getApellido(), empleado.getFecha_nacimiento(), empleado.getFecha_ingreso(), empleado.getEstado(), empleado.getDocumento_identidad(), empleado.getTelefono(), empleado.getId_puesto());
            Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
            empleado.setId_empleado(newId);
        } else {
            String sql = "UPDATE empleado SET nombre = ?, apellido = ?, fecha_nacimiento = ?, fecha_ingreso = ?, estado = ?, documento_identidad = ?, telefono = ?, id_puesto = ? WHERE id_empleado = ?";
            jdbcTemplate.update(sql, empleado.getNombre(), empleado.getApellido(), empleado.getFecha_nacimiento(), empleado.getFecha_ingreso(), empleado.getEstado(), empleado.getDocumento_identidad(), empleado.getTelefono(), empleado.getId_puesto(), empleado.getId_empleado());
        }
        return empleado;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";
        jdbcTemplate.update(sql, id);
    }
}