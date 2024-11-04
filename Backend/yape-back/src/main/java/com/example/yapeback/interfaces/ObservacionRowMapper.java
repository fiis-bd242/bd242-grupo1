package com.example.yapeback.interfaces;

import com.example.yapeback.model.Observacion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObservacionRowMapper implements RowMapper<Observacion> {
    @Override
    public Observacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Observacion observacion = new Observacion();
        observacion.setId_observacion(rs.getLong("id_observacion"));
        observacion.setId_feedback(rs.getLong("id_feedback"));
        observacion.setId_categoria(rs.getLong("id_categoria"));
        observacion.setDescripcion(rs.getString("descripcion"));
        return observacion;
    }
}
