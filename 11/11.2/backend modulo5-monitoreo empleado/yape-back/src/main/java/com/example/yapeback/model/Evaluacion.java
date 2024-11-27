package com.example.yapeback.model;

import java.time.LocalDate;

import lombok.Data;

@Data 
public class Evaluacion {
	private Long idEvaluacion;
    private EstadoEvaluacion estado; // Enum: Pendiente, Finalizada
    private LocalDate  fechaInicio;
    private LocalDate fechaFin;
    private Integer puntaje;
    private String retroalimentacion;
    private Long idEmpleado; // Relación con Empleado

    public enum EstadoEvaluacion {
        Pendiente, Finalizada
    }
}
