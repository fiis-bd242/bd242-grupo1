package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class Entrevista {
    private Long id_entrevista;
    private String estado;
    private LocalDate fecha;
    private Integer puntaje_general;
    private Long id_postulante;
    private Long id_empleado;
    private Long id_feedback;
    private Long id_tipo_entrevista;
    private Feedback feedback;
    private List<EntrevistaIndicador> indicadores;

    // Getters y Setters

    public Long getId_entrevista() {
        return id_entrevista;
    }

    public void setId_entrevista(Long id_entrevista) {
        this.id_entrevista = id_entrevista;
    }

    public Long getId_postulante() {
        return id_postulante;
    }

    public void setId_postulante(Long id_postulante) {
        this.id_postulante = id_postulante;
    }

    public Long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Long getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(Long id_feedback) {
        this.id_feedback = id_feedback;
    }

    public Long getId_tipo_entrevista() {
        return id_tipo_entrevista;
    }

    public void setId_tipo_entrevista(Long id_tipo_entrevista) {
        this.id_tipo_entrevista = id_tipo_entrevista;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPuntaje_general() {
        return puntaje_general;
    }

    public void setPuntaje_general(Integer puntaje_general) {
        this.puntaje_general = puntaje_general;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public List<EntrevistaIndicador> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(List<EntrevistaIndicador> indicadores) {
        this.indicadores = indicadores;
    }
}