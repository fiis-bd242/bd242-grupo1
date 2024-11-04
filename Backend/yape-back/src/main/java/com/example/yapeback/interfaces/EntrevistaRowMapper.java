package com.example.yapeback.interfaces;

import com.example.yapeback.model.Entrevista;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntrevistaRowMapper implements RowMapper<Entrevista> {
    @Override
    public Entrevista mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entrevista entrevista = new Entrevista();
        entrevista.setId_entrevista(rs.getLong("id_entrevista"));
        entrevista.setEstado(rs.getString("estado"));
        entrevista.setFecha(rs.getDate("fecha").toLocalDate());
        entrevista.setPuntaje_general(rs.getInt("puntaje_general"));
        entrevista.setId_postulante(rs.getLong("id_postulante"));
        entrevista.setId_empleado(rs.getLong("id_empleado"));
        entrevista.setId_feedback(rs.getLong("id_feedback"));
        entrevista.setId_tipo_entrevista(rs.getLong("id_tipo_entrevista"));
        return entrevista;
    }
}