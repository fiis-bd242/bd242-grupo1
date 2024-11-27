package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TicketAsigTip {

    private Integer codTicketAsig;
    private Integer codEtiqueta;
    private String problemaIdent;
    private Integer idConv;
    private Integer idEstado;
    private LocalDateTime fechaAsig;
    private String comentario;
    private String nombreEstado;
}
