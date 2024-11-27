package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Educacion {
    private Long id_educacion;
    private String titulo;
    private String institucion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private boolean en_curso;
    private Long id_postulante;
}