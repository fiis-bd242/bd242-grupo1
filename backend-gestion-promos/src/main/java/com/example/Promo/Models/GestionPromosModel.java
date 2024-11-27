package com.example.Promo.Models;

import java.time.LocalDate;

public class GestionPromosModel {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer codPromocion; // Cambiado de String a Integer
    private Double dscto;
    private Double precioFinal;
    private Integer vigencia;
    private String seller;
    private boolean estadoPromo; // Nuevo atributo agregado

    // Getters y Setters existentes

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(Integer codPromocion) {
        this.codPromocion = codPromocion;
    }

    public Double getDscto() {
        return dscto;
    }

    public void setDscto(Double dscto) {
        this.dscto = dscto;
    }

    public Double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    // Nuevo getter y setter para estadoPromo

    public boolean isEstadoPromo() { // Cambié el getter a formato booleano (is)
        return estadoPromo;
    }

    public void setEstadoPromo(boolean estadoPromo) {
        this.estadoPromo = estadoPromo;
    }
}
