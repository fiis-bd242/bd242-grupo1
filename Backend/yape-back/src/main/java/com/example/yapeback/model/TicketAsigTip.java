package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TicketAsigTip {

    private Long codEtiqueta;
    private String problemaIdent;
    private Long idConv;
    private Long idEstado;
    private LocalDateTime fechaAsig;
    private String comentario;
    private String nombreEstado;
}
