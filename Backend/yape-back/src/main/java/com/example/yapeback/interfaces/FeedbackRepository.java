package com.example.yapeback.interfaces;

import com.example.yapeback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@Repository
public class FeedbackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Feedback save(Feedback feedback) {
        String sql = "INSERT INTO feedback (fecha, id_entrevista) VALUES (?, ?)";
        jdbcTemplate.update(sql, feedback.getFecha(), feedback.getId_entrevista());
        return feedback;
    }

    public Optional<Feedback> findById(Long id) {
        String sql = "SELECT * FROM feedback WHERE id_feedback = ?";
        try {
            Feedback feedback = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Feedback.class), id);
            return Optional.ofNullable(feedback);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM feedback WHERE id_feedback = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Feedback> findByEntrevistaId(Long idEntrevista) {
        String sql = "SELECT * FROM feedback WHERE id_entrevista = ?";
        try {
            Feedback feedback = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Feedback.class), idEntrevista);
            return Optional.ofNullable(feedback);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}