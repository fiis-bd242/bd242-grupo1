package com.example.yapeback.interfaces;

import com.example.yapeback.model.Entrevista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EntrevistaRepositoryImpl implements EntrevistaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EntrevistaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Entrevista> findAll() {
        String sql = "SELECT * FROM entrevista";
        return jdbcTemplate.query(sql, new EntrevistaRowMapper());
    }

    @Override
    public Entrevista findById(Long id) {
        String sql = "SELECT * FROM entrevista WHERE id_entrevista = ?";
        return jdbcTemplate.queryForObject(sql, new EntrevistaRowMapper(), id);
    }

    @Override
    public Entrevista save(Entrevista entrevista) {
        String sql = "INSERT INTO entrevista (fecha, estado, id_postulante, id_empleado, id_tipo_entrevista) VALUES (?, ?, ?, ?, ?) RETURNING id_entrevista";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, entrevista.getFecha(), entrevista.getEstado(), entrevista.getId_postulante(), entrevista.getId_empleado(), entrevista.getId_tipo_entrevista());
        entrevista.setId_entrevista(id);
        return entrevista;
    }

    @Override
    public Entrevista update(Long id, Entrevista entrevista) {
        String sql = "UPDATE entrevista SET fecha = ?, estado = ?, id_postulante = ?, id_empleado = ?, id_tipo_entrevista = ? WHERE id_entrevista = ?";
        jdbcTemplate.update(sql, entrevista.getFecha(), entrevista.getEstado(), entrevista.getId_postulante(), entrevista.getId_empleado(), entrevista.getId_tipo_entrevista(), id);
        return entrevista;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM entrevista WHERE id_entrevista = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class EntrevistaRowMapper implements RowMapper<Entrevista> {
        @Override
        public Entrevista mapRow(ResultSet rs, int rowNum) throws SQLException {
            Entrevista entrevista = new Entrevista();
            entrevista.setId_entrevista(rs.getLong("id_entrevista"));
            entrevista.setFecha(rs.getDate("fecha").toLocalDate());
            entrevista.setEstado(rs.getString("estado"));
            entrevista.setId_postulante(rs.getLong("id_postulante"));
            entrevista.setId_empleado(rs.getLong("id_empleado"));
            entrevista.setId_tipo_entrevista(rs.getLong("id_tipo_entrevista"));
            entrevista.setId_feedback(rs.getLong("id_feedback")); // Ensure this line is present
            return entrevista;
        }
    }
}