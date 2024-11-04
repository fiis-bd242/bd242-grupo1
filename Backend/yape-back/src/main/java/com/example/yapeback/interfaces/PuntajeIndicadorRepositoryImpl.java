package com.example.yapeback.interfaces;

import com.example.yapeback.model.PuntajeIndicador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PuntajeIndicadorRepositoryImpl implements PuntajeIndicadorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PuntajeIndicadorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(PuntajeIndicador puntaje) {
        String sql = "INSERT INTO puntaje_indicador (id_entrevista, id_indicador, puntaje) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, puntaje.getId_entrevista(), puntaje.getId_indicador(), puntaje.getPuntaje());
    }

    @Override
    public void update(Long idEntrevista, PuntajeIndicador puntaje) {
        String sql = "UPDATE puntaje_indicador SET puntaje = ? WHERE id_entrevista = ? AND id_indicador = ?";
        jdbcTemplate.update(sql, puntaje.getPuntaje(), idEntrevista, puntaje.getId_indicador());
    }

    @Override
    public List<PuntajeIndicador> findByEntrevistaId(Long idEntrevista) {
        String sql = "SELECT * FROM puntaje_indicador WHERE id_entrevista = ?";
        return jdbcTemplate.query(sql, new PuntajeIndicadorRowMapper(), idEntrevista);
    }

    @Override
    public void deleteByEntrevistaId(Long idEntrevista) {
        String sql = "DELETE FROM puntaje_indicador WHERE id_entrevista = ?";
        jdbcTemplate.update(sql, idEntrevista);
    }
}