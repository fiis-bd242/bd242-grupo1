package com.example.yapeback.interfaces;

import com.example.yapeback.model.Indicador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndicadorRowMapper implements RowMapper<Indicador> {
    @Override
    public Indicador mapRow(ResultSet rs, int rowNum) throws SQLException {
        Indicador indicador = new Indicador();
        indicador.setId_indicador(rs.getLong("id_indicador"));
        indicador.setNombre(rs.getString("nombre"));
        return indicador;
    }
}