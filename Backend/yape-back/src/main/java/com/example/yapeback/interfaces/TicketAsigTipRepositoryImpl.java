package com.example.yapeback.interfaces;

import com.example.yapeback.model.TicketAsigTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class TicketAsigTipRepositoryImpl implements TicketAsigTipRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertTicketAsigTip(TicketAsigTip ticket) {
        String query = """
        INSERT INTO ticket_asig_tip (cod_etiqueta, problema_ident, id_conv, id_estado, fecha_asig, comentario)
        VALUES (?, ?, ?, (SELECT id_estado FROM Estado_ticket_asig WHERE nombre_estado = 'Pendiente'), CURRENT_TIMESTAMP, ?)
        """;

        jdbcTemplate.update(query, ticket.getCodEtiqueta(), ticket.getProblemaIdent(), ticket.getIdConv(), ticket.getComentario());
    }
    @Override
    public boolean actualizarTicket(Long idTicketAsigTip, Long codEtiqueta, String problemaIdent, String nombreEstado, String comentario) {
        String getIdEstadoQuery = "SELECT id_estado FROM estado_ticket_asig WHERE nombre_estado = ?";

        try {
            // Obtener el id_estado basado en el nombre del estado
            Long idEstado = jdbcTemplate.queryForObject(getIdEstadoQuery, new Object[]{nombreEstado}, Long.class);

            // Lógica adicional: Si el estado es "Resuelto" o "No Resuelto", marcar como cerrado
            if ("Resuelto".equalsIgnoreCase(nombreEstado) || "No Resuelto".equalsIgnoreCase(nombreEstado)) {
                comentario = comentario == null || comentario.isEmpty()
                        ? "El ticket ha sido cerrado automáticamente."
                        : comentario;

                // Actualizar la tabla con estado cerrado
                String closeQuery = """
                UPDATE ticket_asig_tip
                SET 
                    cod_etiqueta = ?,      
                    problema_ident = ?,  
                    id_estado = ?,            
                    comentario = ?
                WHERE 
                    cod_ticket_asig = ?;
            """;

                int rowsAffected = jdbcTemplate.update(closeQuery, codEtiqueta, problemaIdent, idEstado, comentario, idTicketAsigTip);
                return rowsAffected > 0;
            } else {
                // Caso general: Mantener el ticket abierto para otros estados
                String updateQuery = """
                UPDATE ticket_asig_tip
                SET 
                    cod_etiqueta = ?,      
                    problema_ident = ?,  
                    id_estado = ?,            
                    comentario = ?           
                WHERE 
                    cod_ticket_asig = ?;
            """;

                int rowsAffected = jdbcTemplate.update(updateQuery, codEtiqueta, problemaIdent, idEstado, comentario, idTicketAsigTip);
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    // Método para obtener un ticket por su ID
    public TicketAsigTip findById(Long idTicketAsigTip) {
        String query = "SELECT * FROM ticket_asig_tip WHERE cod_ticket_asig = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{idTicketAsigTip}, (rs, rowNum) -> {
            TicketAsigTip ticket = new TicketAsigTip();
            ticket.setCodEtiqueta(rs.getLong("cod_etiqueta"));
            ticket.setProblemaIdent(rs.getString("problema_ident"));
            ticket.setIdConv(rs.getLong("id_conv"));
            ticket.setIdEstado(rs.getLong("id_estado"));
            Timestamp timestamp = rs.getTimestamp("fecha_asig");
            ticket.setFechaAsig(timestamp.toLocalDateTime());
            ticket.setComentario(rs.getString("comentario"));
            return ticket;
        });
    }
    @Override
    public List<TicketAsigTip> getAllTickets(Long idEmpleado) {
        String query =
                "SELECT t.cod_ticket_asig, t.problema_ident, t.id_conv, t.id_estado, t.fecha_asig, t.comentario, " +
                        "       ts.nombre_estado, ti.cod_etiqueta " +  // Asegúrate de incluir 'ti.cod_etiqueta'
                        "FROM ticket_asig_tip t " +
                        "JOIN conversacion c ON c.id_conv = t.id_conv " +
                        "JOIN Estado_ticket_asig ts ON ts.id_estado = t.id_estado " +
                        "JOIN tipificacion ti ON ti.cod_etiqueta = t.cod_etiqueta " +
                        "WHERE c.ID_empleado = ?";

        return jdbcTemplate.query(query, new Object[]{idEmpleado}, (rs, rowNum) -> {
            TicketAsigTip ticket = new TicketAsigTip();

            // Set ticket properties
            ticket.setCodEtiqueta(rs.getLong("cod_etiqueta"));  // Mapeo de la columna 'cod_etiqueta'
            ticket.setProblemaIdent(rs.getString("problema_ident"));
            ticket.setIdConv(rs.getLong("id_conv"));
            ticket.setIdEstado(rs.getLong("id_estado"));

            // Set additional fields from the joined tables
            ticket.setNombreEstado(rs.getString("nombre_estado"));

            // Handle date/time conversion
            Timestamp timestamp = rs.getTimestamp("fecha_asig");
            ticket.setFechaAsig(timestamp != null ? timestamp.toLocalDateTime() : null);

            ticket.setComentario(rs.getString("comentario"));

            return ticket;
        });
    }
    @Override
    public List<String> getEstados() {
        String query = "SELECT nombre_estado FROM estado_ticket_asig";
        try {
            return jdbcTemplate.queryForList(query, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los estados", e);
        }
    }


}




