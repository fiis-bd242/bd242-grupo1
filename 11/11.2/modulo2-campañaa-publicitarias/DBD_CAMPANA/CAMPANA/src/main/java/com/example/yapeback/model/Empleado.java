// src/main/java/com/example/yapeback/model/Empleado.java
package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Empleado {
    private Long id_empleado;
    private String nombre;
    private String apellido;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_ingreso;
    private String estado;
    private String documento_identidad;
    private String telefono;
    private Long id_puesto;


}