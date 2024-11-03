package com.example.yapeback.model;

import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
public class Postulante {
    private Long id_postulante;
    private String nombre;
    private Integer telefono;
    private String correo;
    private Long id_vacante;
    private Integer puntaje_general;
    private List<Idioma> idiomas = new ArrayList<>();
    private List<Educacion> educaciones = new ArrayList<>();
    private List<Habilidad> habilidades = new ArrayList<>();
    private List<ExperienciaLaboral> experienciasLaborales = new ArrayList<>();
    private OfertaLaboral ofertaLaboral = new OfertaLaboral();

    // Add the setOfertaLaboral method
    public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
        this.ofertaLaboral = ofertaLaboral;
    }
}