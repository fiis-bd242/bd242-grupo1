package com.example.yapeback.model;

import lombok.Data;

@Data
public class EtiquetaBusqueda {
    private Long codigoEtiqueta;   // Asegúrate de que el tipo coincida con la base de datos
    private String categoria;
    private String tipologia;
    private String funcionalidad;
    private String descripcion;
    private String motivo;  // Nuevo campo para "motivo"
    private Double descripcionSimilarity;
}
