package com.example.yapeback.model;

import lombok.Data;

@Data
public class TicketData {
    private Integer codEtiqueta;
    private String problemaIdent;
    private EstadoTicket nombreEstado;
    private String comentario;

    // Getters y setters
}

