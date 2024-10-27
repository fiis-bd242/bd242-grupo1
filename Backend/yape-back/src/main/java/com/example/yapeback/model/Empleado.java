package com.example.yapeback.model;
import lombok.Data;

import java.sql.Date;

@Data
public class Empleado {
    private Long id_empleado;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    private Date fecha_ingreso;
    private String estado;
    private String documento_identidad;
    private String telefono;
    private Long id_puesto;
}
