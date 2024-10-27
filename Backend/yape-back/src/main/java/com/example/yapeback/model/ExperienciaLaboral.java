package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
public class ExperienciaLaboral {
    private Long id_experiencia;
    private String empresa;
    private String puesto;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Long id_postulante;
    private List<HabilidadExperiencia> habilidades;
}
