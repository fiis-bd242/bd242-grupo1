package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Tipificacion {
    private Long codEtiqueta;      // Código de la etiqueta
    private LocalDateTime fechaCreacion;    // Fecha de creación (para la vista se pondrá la fecha actual)
    private String funcionalidad;  // Funcionalidad asociada
    private String categoria;      // Categoría de la tipificación
    private String tipologia;      // Tipología de la tipificación
    private String motivo;         // Motivo de la tipificación
    private String descripcion;    // Descripción de la tipificación (para creación)
}
