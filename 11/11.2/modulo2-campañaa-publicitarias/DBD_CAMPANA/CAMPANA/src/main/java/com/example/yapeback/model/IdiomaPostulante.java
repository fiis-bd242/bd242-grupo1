// src/main/java/com/example/yapeback/model/IdiomaPostulante.java
package com.example.yapeback.model;

import lombok.Data;

@Data
public class IdiomaPostulante {
    private Long id_idioma;
    private Long id_postulante;
    private String nivel;
}