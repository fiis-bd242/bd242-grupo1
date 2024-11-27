package com.example.yapeback.model;

import lombok.Data;

@Data
public class PrototipoXCanal {
    private Integer cod_prototipo;
    private Integer cod_canal;
    private int impresiones;
    private int clics;
    private int conversiones;
    
    private Double ctr;
    private Double conversionRate;
    private Double cpc;
    private String nombre_canal;
}
