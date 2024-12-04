package com.example.demo.interfaces;

import com.example.demo.model.PostmortemDetalles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostmortemDetallesRepositoryImpl implements PostmortemDetallesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int crearPostmortemDetalle(PostmortemDetalles detalle) {
        String sql = "INSERT INTO postmortem_detalles (resumen_post, impacto, deteccion, recuperacion, severidad, cod_ticket_post) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                detalle.getResumenPost(),
                detalle.getImpacto(),
                detalle.getDeteccion(),
                detalle.getRecuperacion(),
                detalle.getSeveridad(),
                detalle.getCodTicketPost());
    }

    @Override
    public List<Map<String, Object>> obtenerTodos() {
        String sql = "SELECT * FROM postmortem_detalles";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Map<String, Object> obtenerPorId(Long id) {
        String sql = "SELECT * FROM postmortem_detalles WHERE cod_postmortem = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
}
