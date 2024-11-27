// src/main/java/com/example/yapeback/model/Convocatoria.java
package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Convocatoria {
    private Long id_convocatoria;
    private Long id_vacante;
    private String medio_publicacion;
    private Date fecha_inicio; // Change to java.sql.Date
    private Date fecha_fin;    // Change to java.sql.Date
    private String estado;
}
