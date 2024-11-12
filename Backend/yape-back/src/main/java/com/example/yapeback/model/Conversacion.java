package com.example.yapeback.model;

import lombok.Data;

@Data
public class Conversacion {
    private Long idConv;             // ID de la conversación
    private EstadoConv estadoConv;   // Estado de la conversación (Enum)
    private Long idCliente;          // ID del cliente
    private String nombreCompleto;   // Nombre completo del cliente
    private Long idEmpleado;         // ID del empleado
    private Long codTicket;          // Código del ticket

    // Enum para los estados de la conversación
    public enum EstadoConv {
        abierta, cerrada
    }
}
