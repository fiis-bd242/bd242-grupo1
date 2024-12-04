package com.example.demo.model;

import lombok.Data;

@Data 
public class Empleado {
	private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String fechaIngreso;
    private String estado;
    private String documentoIdentidad;
    private String telefono;
    private Integer idPuesto;

}
