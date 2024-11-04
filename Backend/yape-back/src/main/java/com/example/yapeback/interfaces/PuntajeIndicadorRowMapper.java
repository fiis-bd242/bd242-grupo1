package com.example.yapeback.interfaces;

import com.example.yapeback.model.PuntajeIndicador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PuntajeIndicadorRowMapper implements RowMapper<PuntajeIndicador> {
    @Override
    public PuntajeIndicador mapRow(ResultSet rs, int rowNum) throws SQLException {
        PuntajeIndicador puntajeIndicador = new PuntajeIndicador();
        puntajeIndicador.setId_entrevista(rs.getLong("id_entrevista"));
        puntajeIndicador.setId_indicador(rs.getLong("id_indicador"));
        puntajeIndicador.setPuntaje(rs.getInt("puntaje"));
        return puntajeIndicador;
    }
}