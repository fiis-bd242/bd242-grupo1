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

    @Autowired
    private ConversacionRepository conversacionRepository;

    @Override
    public void insertTicketAsigTip(TicketAsigTip ticket,Integer realizadoPor) {
        String query = """
    INSERT INTO ticket_asig_tip (cod_etiqueta, problema_ident, id_conv, id_estado, fecha_asig, comentario)
    VALUES (?, ?, ?, 
            (SELECT id_estado FROM Estado_ticket_asig WHERE nombre_estado = 'Pendiente'), 
            CURRENT_TIMESTAMP, ?)
    RETURNING cod_ticket_asig
    """;

        // Recuperamos el ID generado y lo asignamos a la propiedad codTicketAsig del objeto ticket
        Integer codTicketAsig  = jdbcTemplate.queryForObject(query, new Object[]{
                ticket.getCodEtiqueta(),
                ticket.getProblemaIdent(),
                ticket.getIdConv(),
                ticket.getComentario()
        }, Integer.class);

        // Llamar a insertHistorial con tipo de acción "Asignación"
        insertHistorial(codTicketAsig, ticket.getCodEtiqueta(),"asignacion", realizadoPor);  // Asignamos el ID generado al objeto ticket
    }

    @Override
    public void insertHistorial(Integer codTicketAsig, Integer codEtiqueta,String tipoAccion, Integer realizadoPor) {
        String query = """
        INSERT INTO historial_asignacion 
        (cod_ticket_asig, cod_etiqueta, tipo_accion, realizado_por, fecha_accion)
        VALUES (?, ?, ?, ?, CURRENT_DATE)
    """;

        jdbcTemplate.update(query, codTicketAsig, codEtiqueta, tipoAccion, realizadoPor);
    }

    @Override
    public boolean actualizarTicket(Integer idTicketAsigTip, Integer codEtiqueta, String problemaIdent, String nombreEstado, String comentario, Integer realizadoPor) {
        String getIdEstadoQuery = "SELECT id_estado FROM estado_ticket_asig WHERE nombre_estado = CAST(? AS estado_enum)";
        String getCodTicketAsigQuery = "SELECT cod_ticket_asig FROM ticket_asig_tip WHERE id_conv = ?"; // Nueva consulta para obtener cod_ticket_asig

        try {
            // Obtener el id_estado basado en el nombre del estado
            Long idEstado = jdbcTemplate.queryForObject(getIdEstadoQuery, new Object[]{nombreEstado}, Long.class);

            // Obtener el cod_ticket_asig basado en id_conv
            Integer codTicketAsig = jdbcTemplate.queryForObject(getCodTicketAsigQuery, new Object[]{idTicketAsigTip}, Integer.class);

            // Lógica adicional: Si el estado es "Resuelto" o "No Resuelto", marcar como cerrado
            if ("Resuelto".equalsIgnoreCase(nombreEstado) || "No Resuelto".equalsIgnoreCase(nombreEstado)) {
                comentario = comentario == null || comentario.isEmpty()
                        ? "El ticket ha sido cerrado automáticamente."
                        : comentario;

                // Actualizar el ticket
                String closeQuery = """
        UPDATE ticket_asig_tip
        SET 
            cod_etiqueta = ?,      
            problema_ident = ?,  
            id_estado = ?,            
            comentario = ? 
        WHERE 
            id_conv = ?;
        """;

                int rowsAffected = jdbcTemplate.update(closeQuery, codEtiqueta, problemaIdent, idEstado, comentario, idTicketAsigTip);

                if (rowsAffected > 0) {
                    // Actualizar el estado de la conversación a 'cerrada'
                    conversacionRepository.actualizarEstadoConversacion(idTicketAsigTip, "cerrada");

                    // Registrar la edición en el historial
                    insertHistorial(codTicketAsig, codEtiqueta, "edicion", realizadoPor); // Usamos codTicketAsig en el historial
                    return true;
                }
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
            id_conv = ?;
        """;

                int rowsAffected = jdbcTemplate.update(updateQuery, codEtiqueta, problemaIdent, idEstado, comentario, idTicketAsigTip);

                if (rowsAffected > 0) {
                    // Actualizar el estado de la conversación a 'abierta'
                    conversacionRepository.actualizarEstadoConversacion(idTicketAsigTip, "abierta");

                    // Registrar la edición en el historial
                    insertHistorial(codTicketAsig, codEtiqueta, "edicion", realizadoPor); // Usamos codTicketAsig en el historial
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }



    @Override
    // Método para obtener un ticket por su ID
    public TicketAsigTip findById(Long idTicketAsigTip) {
        String query = "SELECT * FROM ticket_asig_tip WHERE id_conv = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{idTicketAsigTip}, (rs, rowNum) -> {
            TicketAsigTip ticket = new TicketAsigTip();
            ticket.setCodEtiqueta(rs.getInt("cod_etiqueta"));
            ticket.setProblemaIdent(rs.getString("problema_ident"));
            ticket.setIdConv(rs.getInt("id_conv"));
            ticket.setIdEstado(rs.getInt("id_estado"));
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
            ticket.setCodEtiqueta(rs.getInt("cod_etiqueta"));  // Mapeo de la columna 'cod_etiqueta'
            ticket.setProblemaIdent(rs.getString("problema_ident"));
            ticket.setIdConv(rs.getInt("id_conv"));
            ticket.setIdEstado(rs.getInt("id_estado"));

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
        // Ajusta la consulta SQL para usar el parámetro
        String query = "SELECT nombre_estado FROM estado_ticket_asig ";

        try {
            // Pasamos el parámetro 'estadoNombre' para completar la consulta
            return jdbcTemplate.queryForList(query, String.class); // Esto pasa el valor de 'estadoNombre'
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los estados", e);
        }
    }




}




