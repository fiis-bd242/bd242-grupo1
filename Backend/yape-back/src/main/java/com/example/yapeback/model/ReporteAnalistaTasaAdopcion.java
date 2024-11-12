package com.example.yapeback.model;

import lombok.Data;

@Data

public class ReporteAnalistaTasaAdopcion {
    private String idAnalista;
    private int totalSugerencias;
    private int sugerenciasAdoptadas;
    private double tasaAdopcion;


}
