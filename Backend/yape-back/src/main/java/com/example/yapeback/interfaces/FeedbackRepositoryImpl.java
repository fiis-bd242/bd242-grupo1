package com.example.yapeback.interfaces;

import com.example.yapeback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FeedbackRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Feedback save(Feedback feedback) {
        String sql = "INSERT INTO feedback (fecha) VALUES (?) RETURNING id_feedback";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, feedback.getFecha());
        feedback.setId_feedback(id);
        return feedback;
    }

    @Override
    public Feedback findById(Long id) {
        String sql = "SELECT * FROM feedback WHERE id_feedback = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new FeedbackRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM feedback WHERE id_feedback = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class FeedbackRowMapper implements RowMapper<Feedback> {
        @Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feedback feedback = new Feedback();
            feedback.setId_feedback(rs.getLong("id_feedback"));
            feedback.setFecha(rs.getDate("fecha").toLocalDate());
            return feedback;
        }
    }
}