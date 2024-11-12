package com.example.yapeback.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteAnalistaTasaAdopcion {
    private String idAnalista;
    private int totalSugerencias;
    private int sugerenciasAdoptadas;
    private double tasaAdopcion;


}
