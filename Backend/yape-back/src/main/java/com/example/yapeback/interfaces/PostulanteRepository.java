package com.example.yapeback.interfaces;

import com.example.yapeback.model.*;
import java.util.List;

public interface PostulanteRepository {
    List<Postulante> findAll();
    Postulante findById(Long id);
    Postulante save(Postulante postulante);
    void deleteById(Long id);
    List<Idioma> findIdiomasByPostulanteId(Long idPostulante);
    void saveIdioma(Long idPostulante, Idioma idioma);
    void deleteIdiomasByPostulanteId(Long idPostulante);
    void saveEducacion(Long idPostulante, Educacion educacion);
    void deleteEducacionesByPostulanteId(Long idPostulante);
    void saveHabilidad(Long idPostulante, Habilidad habilidad);
    void deleteHabilidadesByPostulanteId(Long idPostulante);
    void saveExperiencia(Long idPostulante, ExperienciaLaboral experiencia);
    void deleteExperienciasByPostulanteId(Long idPostulante);
    List<Entrevista> findEntrevistasByPostulanteId(Long idPostulante);
    List<Educacion> findEducacionesByPostulanteId(Long idPostulante);
    List<Habilidad> findHabilidadesByPostulanteId(Long idPostulante);
    Long saveIdiomaAndGetId(String nombre);
    Long saveHabilidadExperienciaAndGetId(String nombre, Long idExperiencia);
    Long saveExperienciaAndGetId(Long idPostulante, ExperienciaLaboral experiencia);
}