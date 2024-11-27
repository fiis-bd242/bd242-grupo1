package com.example.Promo.Models;

public class PromocionesActivasModel {

    private String fechaInicio;
    private Integer codPromocion;
    private Integer precioFinal;
    private Double dscto;
    private String seller;
    private String vigencia;
    private String descripcion;

    // Getters and Setters
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(Integer codPromocion) {
        this.codPromocion = codPromocion;
    }

    public Integer getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Integer precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Double getDscto() {
        return dscto;
    }

    public void setDscto(Double dscto) {
        this.dscto = dscto;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}