package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Notificacion {
    private Long idNoti;
    private LocalDateTime fechaEnvio;
    private String mensaje;
    private String tipoNoti;  // Puede ser 'sugerencia' o 'edicion'
    private Long codTicketAsig; // Representa el cod_ticket_asig desde TicketAsigTip
    private Long codEtiqueta;   // Representa el cod_etiqueta desde Tipificacion
    private Long idEmpleado;    // Representa el id_empleado desde Empleado
}
