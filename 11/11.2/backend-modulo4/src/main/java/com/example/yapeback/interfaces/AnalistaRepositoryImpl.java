package com.example.yapeback.interfaces;

import com.example.yapeback.model.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnalistaRepositoryImpl implements AnalistaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertarSugerencia(Notificacion notificacion) {
        String sql = "INSERT INTO notificacion (fecha_envio, mensaje, tipo_noti, cod_ticket_asig, cod_etiqueta, ID_empleado) VALUES (CURRENT_TIMESTAMP, ?, 'sugerencia', ?, ?, ?)";
        jdbcTemplate.update(sql, notificacion.getMensaje(), notificacion.getCodTicketAsig(), notificacion.getCodEtiqueta(), notificacion.getIdEmpleado());
    }


    @Override
    public void actualizarEtiquetaAsignada(int idTicketAsigTip, int etiqueta, int etiquetaCambiada, String mensajeAlAsesor,int idEmpleado) {
        // Actualiza la etiqueta en la tabla Ticket_asig_tip
        String updateSql = """
        UPDATE Ticket_asig_tip
        SET cod_etiqueta = ?, bloqueado = TRUE, fecha_asig = CURRENT_TIMESTAMP
        WHERE cod_ticket_asig = ? AND bloqueado = FALSE
        """;
        jdbcTemplate.update(updateSql, etiquetaCambiada, idTicketAsigTip);

        // Inserta una nueva notificación en la tabla Notificacion
        String insertNotificacionSql = """
        INSERT INTO Notificacion (fecha_envio, mensaje, tipo_noti, cod_ticket_asig, cod_etiqueta, id_empleado)
        VALUES (CURRENT_TIMESTAMP, ?, 'edicion', ?, ?, ?)
        """;
        jdbcTemplate.update(insertNotificacionSql, mensajeAlAsesor, idTicketAsigTip, etiquetaCambiada, idEmpleado);
    }


}
