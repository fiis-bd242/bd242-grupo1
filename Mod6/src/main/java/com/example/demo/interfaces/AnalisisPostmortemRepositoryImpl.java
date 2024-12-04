package com.example.demo.interfaces;

import com.example.demo.model.AnalisisPostmortem;
import com.example.demo.interfaces.AnalisisPostmortemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AnalisisPostmortemRepositoryImpl implements AnalisisPostmortemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int crearAnalisisPostmortem(AnalisisPostmortem analisis) {
        String sql = "INSERT INTO analisis_post (aprobacion_analisis, fecha_inicio_post, fecha_fin_post, cod_postmortem) " +
                     "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, analisis.getAprobacionAnalisis(), analisis.getFechaInicioPost(),
                analisis.getFechaFinPost(), analisis.getCodPostmortem());
    }

    @Override
    public List<Map<String, Object>> obtenerTodos() {
        String sql = "SELECT * FROM analisis_post";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Map<String, Object> obtenerPorId(Long id) {
        String sql = "SELECT * FROM analisis_post WHERE cod_analisis_post = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
}
