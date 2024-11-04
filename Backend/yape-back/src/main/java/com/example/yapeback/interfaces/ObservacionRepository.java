package com.example.yapeback.interfaces;

import com.example.yapeback.model.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@Repository
public class ObservacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Observacion save(Observacion observacion) {
        String sql = "INSERT INTO observacion (id_feedback, nombre, descripcion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, observacion.getId_feedback(), observacion.getNombre(), observacion.getDescripcion());
        return observacion;
    }

    public Observacion update(Observacion observacion) {
        String sql = "UPDATE observacion SET id_feedback = ?, nombre = ?, descripcion = ? WHERE id_observacion = ?";
        jdbcTemplate.update(sql, observacion.getId_feedback(), observacion.getNombre(), observacion.getDescripcion(), observacion.getId_observacion());
        return observacion;
    }

    public Optional<Observacion> findById(Long id) {
        String sql = "SELECT * FROM observacion WHERE id_observacion = ?";
        try {
            Observacion observacion = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Observacion.class), id);
            return Optional.ofNullable(observacion);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM observacion WHERE id_observacion = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Observacion> findByFeedbackId(Long idFeedback) {
        String sql = "SELECT * FROM observacion WHERE id_feedback = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Observacion.class), idFeedback);
    }
}