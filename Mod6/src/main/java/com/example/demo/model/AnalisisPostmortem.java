package com.example.demo.model;

import java.util.Date;

public class AnalisisPostmortem {
    private Long cod_analisis_post;
    private String aprobacion_analisis;
    private Date fecha_inicio_post;
    private Date fecha_fin_post;
    private Long cod_postmortem;

    // Getters y Setters
    public Long getCodAnalisisPost() {
        return cod_analisis_post;
    }

    public void setCodAnalisisPost(Long codAnalisisPost) {
        this.cod_analisis_post = codAnalisisPost;
    }

    public String getAprobacionAnalisis() {
        return aprobacion_analisis;
    }

    public void setAprobacionAnalisis(String aprobacionAnalisis) {
        this.aprobacion_analisis = aprobacionAnalisis;
    }

    public Date getFechaInicioPost() {
        return fecha_inicio_post;
    }

    public void setFechaInicioPost(Date fechaInicioPost) {
        this.fecha_inicio_post = fechaInicioPost;
    }

    public Date getFechaFinPost() {
        return fecha_fin_post;
    }

    public void setFechaFinPost(Date fechaFinPost) {
        this.fecha_fin_post = fechaFinPost;
    }

    public Long getCodPostmortem() {
        return cod_postmortem;
    }

    public void setCodPostmortem(Long codPostmortem) {
        this.cod_postmortem = codPostmortem;
    }
}
