package com.example.yapeback.interfaces;

import com.example.yapeback.model.Observacion;
import com.example.yapeback.model.TipoEntrevistaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ObservacionRepositoryImpl implements ObservacionRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ObservacionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Observacion observacion) {
        String sql = "INSERT INTO observacion (id_feedback, id_categoria, descripcion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, observacion.getId_feedback(), observacion.getId_categoria(), observacion.getDescripcion());
    }

    @Override
    public List<Observacion> findByFeedbackId(Long feedbackId) {
        String sql = "SELECT o.*, c.nombre AS categoriaNombre FROM observacion o " +
                "JOIN categoria_observacion c ON o.id_categoria = c.id_categoria " +
                "WHERE o.id_feedback = ?";
        return jdbcTemplate.query(sql, new ObservacionRowMapper(), feedbackId);
    }
    private static final class ObservacionRowMapper implements RowMapper<Observacion> {
        @Override
        public Observacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Observacion observacion = new Observacion();
            observacion.setId_observacion(rs.getLong("id_observacion"));
            observacion.setId_feedback(rs.getLong("id_feedback"));
            observacion.setId_categoria(rs.getLong("id_categoria"));
            observacion.setDescripcion(rs.getString("descripcion"));
            observacion.setCategoriaNombre(rs.getString("categoriaNombre")); // Ensure this line is present
            return observacion;
        }
    }

    @Override
    public void deleteByFeedbackId(Long idFeedback) {
        String sql = "DELETE FROM observacion WHERE id_feedback = ?";
        jdbcTemplate.update(sql, idFeedback);
    }

    @Override
    public boolean existsCategoriaById(Long idCategoria) {
        String sql = "SELECT COUNT(*) FROM categoria WHERE id_categoria = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idCategoria);
        return count != null && count > 0;
    }




}