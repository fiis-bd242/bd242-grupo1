package com.example.yapeback.interfaces;

import com.example.yapeback.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public List<Empleado> findAll() {
        String sql = "SELECT * FROM empleado";
        return jdbcTemplate.query(sql, new EmpleadoRowMapper());
    }

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