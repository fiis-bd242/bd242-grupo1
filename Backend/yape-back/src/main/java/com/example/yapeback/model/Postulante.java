package com.example.yapeback.model;

import lombok.Data;
import java.util.List;

@Data
public class Postulante {
    private Long id_postulante;
    private String nombre;
    private int telefono;
    private Long id_vacante;
    private List<Idioma> idiomas;
    private List<Educacion> educaciones;
    private List<Habilidad> habilidades;
    private List<ExperienciaLaboral> experienciasLaborales;
    private OfertaLaboral ofertaLaboral;
}