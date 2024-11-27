package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Asistencia {
    private Long idAsistencia;
    private EstadoAsistencia estado; // Enum: Presente, Ausente, Tarde
    private LocalDate fecha;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private Long idEmpleado; // Relación con Empleado

    public enum EstadoAsistencia {
        Presente, Ausente, Tarde
    }
}
