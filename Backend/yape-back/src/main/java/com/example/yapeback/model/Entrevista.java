package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Entrevista {
    private Long id_entrevista;
    private String estado;
    private LocalDate fecha;
    private int puntaje_general;
    private Long id_postulante;
    private Long id_empleado;
    private String tipo_entrevista;
}