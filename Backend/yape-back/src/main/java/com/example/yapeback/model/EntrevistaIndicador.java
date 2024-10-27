// src/main/java/com/example/yapeback/model/EntrevistaIndicador.java
package com.example.yapeback.model;

import lombok.Data;

@Data
public class EntrevistaIndicador {
    private Long id_entrevista;
    private Long id_indicador;
    private Integer puntaje;
}