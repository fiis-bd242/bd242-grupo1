// src/main/java/com/example/yapeback/interfaces/BeneficioRepositoryImpl.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Beneficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BeneficioRepositoryImpl implements BeneficioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Beneficio> findAll() {
        String sql = "SELECT * FROM beneficio";
        return jdbcTemplate.query(sql, new BeneficioRowMapper());
    }

    @Override
    public Beneficio findById(Long id) {
        String sql = "SELECT * FROM beneficio WHERE id_beneficio = ?";
        return jdbcTemplate.queryForObject(sql, new BeneficioRowMapper(), id);
    }

    @Override
    public Beneficio save(Beneficio beneficio) {
        if (beneficio.getId_beneficio() == null) {
            String sql = "INSERT INTO beneficio (descripcion) VALUES (?)";
            jdbcTemplate.update(sql, beneficio.getDescripcion());
            Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
            beneficio.setId_beneficio(newId);
        } else {
            String sql = "UPDATE beneficio SET descripcion = ? WHERE id_beneficio = ?";
            jdbcTemplate.update(sql, beneficio.getDescripcion(), beneficio.getId_beneficio());
        }
        return beneficio;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM beneficio WHERE id_beneficio = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class BeneficioRowMapper implements RowMapper<Beneficio> {
        @Override
        public Beneficio mapRow(ResultSet rs, int rowNum) throws SQLException {
            Beneficio beneficio = new Beneficio();
            beneficio.setId_beneficio(rs.getLong("id_beneficio"));
            beneficio.setDescripcion(rs.getString("descripcion"));
            return beneficio;
        }
    }
}