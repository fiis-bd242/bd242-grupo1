package com.example.yapeback.model;

import lombok.Data;

@Data
public class ReporteTipificacionDetallado {
    private String categoria;
    private String funcionalidad;
    private String motivo;
    private String tipologia;
    private Integer volumenTipificacion;
    private String periodo;
}
