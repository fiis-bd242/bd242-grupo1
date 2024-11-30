package com.example.Promo.Models;

import java.util.List;

public class AsignarProductoRequest {
    private Integer codPromocion;
    private List<Integer> codigosProductos;

    public Integer getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(Integer codPromocion) {
        this.codPromocion = codPromocion;
    }

    public List<Integer> getCodigosProductos() {
        return codigosProductos;
    }

    public void setCodigosProductos(List<Integer> codigosProductos) {
        this.codigosProductos = codigosProductos;
    }
}
