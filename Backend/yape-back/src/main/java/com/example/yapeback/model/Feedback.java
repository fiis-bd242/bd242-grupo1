package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class Feedback {
    private Long id_feedback;
    private LocalDate fecha;
    private Long id_entrevista;
    private List<Observacion> observaciones;

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}