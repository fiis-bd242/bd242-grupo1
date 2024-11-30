package com.example.yapeback.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistorialTipificacion {
    private LocalDateTime fecha;      // Fecha de la acción
    private String responsable;      // Responsable (empleado asignador)
    private String categoria;        // Categoría de la tipificación
    private String funcionalidad;    // Funcionalidad de la tipificación
    private String motivo;           // Motivo de la tipificación
    private String tipologia;        // Tipología de la tipificación
    private Long codEtiqueta;
}
