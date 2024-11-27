package com.example.yapeback.model;

import lombok.Data;


import java.time.LocalDateTime;



@Data
public class Prototipo {
    private Integer cod_prototipo;
    private String nombre_prot;
    private String descripcion;
    private double prop_presupuesto;
    private LocalDateTime Fecha_creacion;
    private LocalDateTime Fecha_estado;
    private String Prop_audiencia;
    private Integer ID_empleado;
    private Integer cod_est_prot;
}
