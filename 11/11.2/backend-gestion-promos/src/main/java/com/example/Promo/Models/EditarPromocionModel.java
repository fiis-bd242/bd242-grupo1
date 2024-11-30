package com.example.Promo.Models;

import java.time.LocalDate;

public class EditarPromocionModel {
    private Long codPromocion;
    private String fecha_inicio;
    private String fecha_fin;
    private Double dscto;
    private Integer precio_final;
    private String dscrip_Promo; // <-- Cambiado para coincidir con la base de datos

    // Getters y Setters


    public Long getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(Long codPromocion) {
        this.codPromocion = codPromocion;
    }


    public Double getDscto() {
        return dscto;
    }

    public void setDscto(Double dscto) {
        this.dscto = dscto;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getDscrip_Promo() {
        return dscrip_Promo;
    }

    public void setDscrip_Promo(String dscrip_Promo) {
        this.dscrip_Promo = dscrip_Promo;
    }

    public Integer getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(Integer precio_final) {
        this.precio_final = precio_final;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}