package com.example.yapeback.model;

import lombok.Data;

@Data
public class Observacion {
    private Long id_observacion;
    private Long id_feedback;
    private Long id_categoria;
    private String descripcion;
    private String categoriaNombre; // Añadir este campo
}
