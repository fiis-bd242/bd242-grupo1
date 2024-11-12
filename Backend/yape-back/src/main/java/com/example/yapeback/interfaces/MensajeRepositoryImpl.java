package com.example.yapeback.interfaces;

import com.example.yapeback.model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MensajeRepositoryImpl implements MensajeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para obtener los mensajes de una conversación filtrados por el idEmpleado (asesor) y el id_cliente (cliente)
    @Override
    public List<Mensaje> findMessagesByConversationAndAdvisor(Long idConv, Long idEmpleado, Long idCliente) {
        String query = """
            SELECT m.id_mensaje, 
                   m.contenido, 
                   m.id_conv, 
                   m.remitente_cliente, 
                   m.remitente_asesor
            FROM mensaje m
            WHERE m.id_conv = ? 
            AND (m.remitente_asesor = ? OR m.remitente_cliente = ?)
            """;

        return jdbcTemplate.query(query, new MensajeRowMapper(), idConv, idEmpleado, idCliente);
    }

    private static class MensajeRowMapper implements RowMapper<Mensaje> {
        @Override
        public Mensaje mapRow(ResultSet rs, int rowNum) throws SQLException {
            Mensaje mensaje = new Mensaje();
            mensaje.setIdMensaje(rs.getLong("id_mensaje"));
            mensaje.setContenido(rs.getString("contenido"));
            mensaje.setRemitenteCliente(rs.getLong("remitente_cliente"));
            mensaje.setRemitenteAsesor(rs.getLong("remitente_asesor"));
            return mensaje;
        }
    }
}
