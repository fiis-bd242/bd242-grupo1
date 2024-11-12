package com.example.yapeback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteBusiness {
    private String categoria;
    private String tipologia;
    private String funcionalidad;
    private String motivo;
    private int volumenContacto;
}
