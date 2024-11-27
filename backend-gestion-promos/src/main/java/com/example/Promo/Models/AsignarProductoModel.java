package com.example.Promo.Models;

public class AsignarProductoModel {
    private Integer codPromocion;
    private Integer codProducto;

    public AsignarProductoModel() {}

    public AsignarProductoModel(Integer codPromocion, Integer codProducto) {
        this.codPromocion = codPromocion;
        this.codProducto = codProducto;
    }

    // Getters y setters
    public Integer getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(Integer codPromocion) {
        this.codPromocion = codPromocion;
    }

    public Integer getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Integer codProducto) {
        this.codProducto = codProducto;
    }
}