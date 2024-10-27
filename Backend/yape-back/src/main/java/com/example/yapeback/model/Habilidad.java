package com.example.yapeback.model;

import lombok.Data;

@Data
public class Habilidad {
    private Long id_habilidad_postulante;
    private String nombre;
    private String nivel;
    private Long id_postulante;
}
