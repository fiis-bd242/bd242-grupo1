package com.example.yapeback.model;

import lombok.Data;

@Data
public class Notificacion {
    private int codTicketAsig;    // Código del ticket asignado
    private int codEtiqueta;      // Código de la etiqueta
    private String tipo;          // Tipo de notificación (sugerencia, edición, etc.)
    private String mensaje;       // Mensaje de la notificación
    private int idEmpleado;       // ID del empleado que genera la notificación
}
