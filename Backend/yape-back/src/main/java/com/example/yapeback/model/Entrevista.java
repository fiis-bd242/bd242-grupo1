package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
public class Entrevista {
    private Long id_entrevista;
    private Long id_postulante;
    private Long id_empleado;
    private Long id_feedback;
    private Long id_tipo_entrevista;
    private Date fecha;
    private String estado;
    private Integer puntaje_general;
    private Feedback feedback;
    private List<EntrevistaIndicador> indicadores;
}