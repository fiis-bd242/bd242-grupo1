package com.example.yapeback.model;

import lombok.Data;
import java.util.List;

@Data
public class Entrevista {
    private Long id_entrevista;
    private Long id_feedback;
    private java.time.LocalDate fecha;
    private String estado;
    private Long id_postulante;
    private Long id_empleado;
    private Long id_tipo_entrevista;
    private int puntaje_general;
    private List<EntrevistaIndicador> indicadores;
    private Feedback feedback;

    public void setIndicadores(List<EntrevistaIndicador> indicadores) {
        this.indicadores = indicadores;
    }
}