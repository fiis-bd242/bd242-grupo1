package com.example.yapeback.interfaces;

import com.example.yapeback.model.Feedback;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackRowMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId_feedback(rs.getLong("id_feedback"));
        feedback.setFecha(rs.getDate("fecha"));
        // Map other necessary fields
        return feedback;
    }
}
