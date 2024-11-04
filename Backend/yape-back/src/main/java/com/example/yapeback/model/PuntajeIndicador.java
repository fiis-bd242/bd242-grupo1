package com.example.yapeback.model;

import lombok.Data;

@Data
public class PuntajeIndicador {
    private Long id_puntaje_indicador;
    private Long id_entrevista;
    private Long id_indicador;
    private int puntaje;
}