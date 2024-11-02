package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
public class Entrevista {
    private Long id_entrevista;
    private Long id_postulante;
    private Long id_empleado;
    private Long id_feedback;
    private Long id_tipo_entrevista;
    private LocalDate fecha;
    private String estado;
    private Integer puntaje_general;
    private Feedback feedback;
    private List<EntrevistaIndicador> indicadores;

    // The @Data annotation from Lombok already generates setters,
    // but if custom setters are needed, ensure they handle types correctly.
}