package com.example.yapeback.model;

import lombok.Data;

@Data
public class ReporteClientesActivos {
    private String empresaCli;
    private Integer clientesMasActivos;
    private String periodo;
}
