package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data 
public class Permiso {
	private Long idPermiso;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private EstadoPermiso estado; // Enum: Pendiente, Aprobado, Rechazado
    private String comentario; // Justificación del empleado
    private String comentarioAdmin; // Comentario del administrador en caso de rechazo
    private Long idEmpleado; // Relación con Empleado
    private Long idTipoPermiso; // Relación con TipoPermiso
    private LocalDateTime fechaCambioEstado;

    public enum EstadoPermiso {
        Pendiente, Aprobado, Rechazado
    }
}
