package com.example.yapeback.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnalistaRepositoryImpl implements AnalistaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertarSugerencia(int codTicketAsig, int codEtiqueta, String tipo, String mensaje) {
        String sql = "INSERT INTO notificacion (cod_ticket_asig, cod_etiqueta, tipo_noti, mensaje) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, codTicketAsig, codEtiqueta, tipo, mensaje);
    }

    @Override
    public void actualizarEtiquetaAsignada(int idTicketAsigTip, int nuevoCodEtiqueta) {
        String sql = """
            UPDATE Ticket_asig_tip
            SET cod_etiqueta = ?, bloqueado = TRUE, fecha_asig = CURRENT_TIMESTAMP
            WHERE cod_ticket_asig = ? AND bloqueado = FALSE
            """;
        jdbcTemplate.update(sql, nuevoCodEtiqueta, idTicketAsigTip);
    }
}
