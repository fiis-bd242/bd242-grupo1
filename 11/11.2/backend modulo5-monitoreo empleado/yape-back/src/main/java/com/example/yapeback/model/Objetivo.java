package com.example.yapeback.model;

import java.time.LocalDate;
import lombok.Data;

@Data 
public class Objetivo {
	private Long idObjetivo;
    private String descripcion;
    private EstadoObjetivo estado; // Enum: Pendiente, Cumplido, NoCumplido, Cancelado
    private LocalDate  fechaInicio;
    private LocalDate fechaFin;
    private Long idEmpleado; // Relación con Empleado
    private Long idPrioridadObjetivo; // Relación con PrioridadObjetivo

    public enum EstadoObjetivo {
        Pendiente, Cumplido, No_cumplido, Cancelado
    }
}
