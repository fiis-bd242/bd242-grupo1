package com.example.yapeback.model;

import lombok.Data;

@Data
public class EtiquetaBusqueda {
    private Long codigoEtiqueta;
    private String categoria;
    private String tipologia;
    private String funcionalidad;
    private String descripcion;
    private Double descripcionSimilarity;
}
