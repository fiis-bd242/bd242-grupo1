package com.example.demo.interfaces;


import com.example.demo.model.Incidente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class IncidenteRepositoryImpl implements IncidenteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear un incidente
    @Override
    public int crearIncidente(Incidente incidente) {
        String sql = "INSERT INTO Incidente (mensaje_incidente, categoria, cod_ticket_inc) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, incidente.getMensajeIncidente(), incidente.getCategoria(), incidente.getCodTicketInc());
    }

    // Obtener todos los incidentes
    @Override
    public List<Map<String, Object>> obtenerTodos() {
        String sql = "SELECT * FROM Incidente";
        return jdbcTemplate.queryForList(sql);
    }

    // Obtener un incidente por ID
    @Override
    public Map<String, Object> obtenerPorId(Long id) {
        String sql = "SELECT * FROM Incidente WHERE cod_incidente = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    // Eliminar un incidente por ID
    @Override
    public int eliminarIncidente(Long id) {
        String sql = "DELETE FROM Incidente WHERE cod_incidente = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Actualizar un incidente
    @Override
    public int actualizarIncidente(Long id, Incidente incidente) {
        String sql = "UPDATE Incidente SET mensaje_incidente = ?, categoria = ?, cod_ticket_inc = ? WHERE cod_incidente = ?";
        return jdbcTemplate.update(sql, incidente.getMensajeIncidente(), incidente.getCategoria(), incidente.getCodTicketInc(), id);
    }
}
