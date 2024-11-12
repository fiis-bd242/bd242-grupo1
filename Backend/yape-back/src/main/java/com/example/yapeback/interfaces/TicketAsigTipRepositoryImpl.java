package com.example.yapeback.interfaces;

import com.example.yapeback.model.TicketAsigTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketAsigTipRepositoryImpl implements TicketAsigTipRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    // Método para insertar un nuevo ticket de asignación de tipificación
    public void insertTicketAsigTip(TicketAsigTip ticket) {
        String query = """
            INSERT INTO ticket_asig_tip (cod_etiqueta, problema_ident, id_conv, id_estado, fecha_asig, comentario)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(query, ticket.getCodEtiqueta(), ticket.getProblemaIdent(), ticket.getIdConv(),
                ticket.getIdEstado(), ticket.getFechaAsig(), ticket.getComentario());
    }
    @Override
    public boolean actualizarTicket(Long idTicketAsigTip, Long codEtiqueta, String problemaIdent, Long idConv, Long idEstado, String comentario) {
        String sql = """
        UPDATE ticket_asig_tip
        SET 
            cod_etiqueta = ?,      
            problema_ident = ?,  
            id_conv = ?,               
            id_estado = ?,            
            fecha_asig = CURRENT_TIMESTAMP,    
            comentario = ?           
        WHERE 
            cod_ticket_asig = ?;
        """;

        try {
            int rowsAffected = jdbcTemplate.update(sql, codEtiqueta, problemaIdent, idConv, idEstado, comentario, idTicketAsigTip);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
