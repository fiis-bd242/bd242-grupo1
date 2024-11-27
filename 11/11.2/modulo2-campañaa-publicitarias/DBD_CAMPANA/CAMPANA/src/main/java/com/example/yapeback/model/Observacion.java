package com.example.yapeback.model;

import lombok.Data;

@Data
public class Observacion {
    private Long id_observacion;
    private Long id_entrevista;
    private String nombre;
    private String descripcion;
}