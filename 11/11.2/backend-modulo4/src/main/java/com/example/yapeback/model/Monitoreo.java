package com.example.yapeback.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Monitoreo {
    private String codEtiqueta;
    private LocalDateTime fechaAccion;
    private String responsable;
    private String comentario;
    private String tipoAccion;
    private String nombreEstado;
}
