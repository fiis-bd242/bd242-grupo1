package com.example.yapeback.model;

import lombok.Data;

@Data 
public class EvaluacionObjetivo {
    private Long idEvaluacionObjetivo;
    private EstadoEvalObjetivo estado; // Enum: Cumplido, NoCumplido, EnProgreso, Cancelado
    private Long idObjetivo; // Relación con Objetivo
    private Long idEvaluacion; // Relación con Evaluacion

    public enum EstadoEvalObjetivo {
        Cumplido, No_cumplido, En_progreso, Cancelado
    }
}
