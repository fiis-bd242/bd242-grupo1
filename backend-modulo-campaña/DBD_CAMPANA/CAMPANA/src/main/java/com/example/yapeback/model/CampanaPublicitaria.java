package com.example.yapeback.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CampanaPublicitaria {
    private Integer cod_campana;
    private String Resultado;
    private Double Presupuesto;
    private String Nombre_campana;
    private String Audiencia;
    private LocalDateTime fecha_publicacion;
    private LocalDateTime fecha_finalizacion;
    private Integer cod_promocion;
    private Integer cod_prototipo;
}
