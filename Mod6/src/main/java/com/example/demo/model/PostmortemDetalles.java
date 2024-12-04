package com.example.demo.model;

public class PostmortemDetalles {

    private Long cod_postmortem;
    private String resumen_post;
    private String impacto;
    private String deteccion;
    private String recuperacion;
    private String severidad;
    private Long cod_ticket_post;

    // Getters y Setters
    public Long getCodPostmortem() {
        return cod_postmortem;
    }

    public void setCodPostmortem(Long codPostmortem) {
        this.cod_postmortem = codPostmortem;
    }

    public String getResumenPost() {
        return resumen_post;
    }

    public void setResumenPost(String resumenPost) {
        this.resumen_post = resumenPost;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getDeteccion() {
        return deteccion;
    }

    public void setDeteccion(String deteccion) {
        this.deteccion = deteccion;
    }

    public String getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public Long getCodTicketPost() {
        return cod_ticket_post;
    }

    public void setCodTicketPost(Long codTicketPost) {
        this.cod_ticket_post = codTicketPost;
    }
}
