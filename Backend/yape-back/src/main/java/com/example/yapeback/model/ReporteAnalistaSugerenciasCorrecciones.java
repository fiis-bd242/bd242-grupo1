package com.example.yapeback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteAnalistaSugerenciasCorrecciones {
    private String idAnalista;
    private int cantidadSugerencias;
    private int cantidadCorrecciones;
}
