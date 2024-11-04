package com.example.yapeback.model;

import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Data
public class Entrevista {
    private Long id_entrevista;
    private String estado;
    private LocalDate fecha;
    private int puntaje_general;
    private Long id_postulante;
    private Long id_empleado;
    private Long id_feedback;
    private Long id_tipo_entrevista;
    private List<EntrevistaIndicador> indicadores;
    private Feedback feedback;
}