package com.example.yapeback.interfaces;

import com.example.yapeback.model.Indicador;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IndicadorRepositoryImpl implements IndicadorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IndicadorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Indicador findById(Long id) {
        String sql = "SELECT * FROM indicador WHERE id_indicador = ?";
        return jdbcTemplate.queryForObject(sql, new IndicadorRowMapper(), id);
    }

    private static final class IndicadorRowMapper implements RowMapper<Indicador> {
        @Override
        public Indicador mapRow(ResultSet rs, int rowNum) throws SQLException {
            Indicador indicador = new Indicador();
            indicador.setId_indicador(rs.getLong("id_indicador"));
            indicador.setNombre(rs.getString("nombre"));
            return indicador;
        }
    }
}
