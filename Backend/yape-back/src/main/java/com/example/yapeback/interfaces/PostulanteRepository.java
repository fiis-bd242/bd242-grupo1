// src/main/java/com/example/yapeback/interfaces/PostulanteRepository.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.*;
import java.util.List;

public interface PostulanteRepository {
    List<Postulante> findAll();
    Postulante findById(Long id);
    Postulante save(Postulante postulante);
    void deleteById(Long id);
    List<Idioma> findIdiomasByPostulanteId(Long idPostulante);
    List<Educacion> findEducacionesByPostulanteId(Long idPostulante);
    List<Habilidad> findHabilidadesByPostulanteId(Long idPostulante);
    List<ExperienciaLaboral> findExperienciasByPostulanteId(Long idPostulante);
    List<HabilidadExperiencia> findHabilidadesByExperienciaId(Long idExperiencia);
    Feedback findFeedbackByEntrevistaId(Long idEntrevista);
    Feedback saveFeedback(Feedback feedback);
    void deleteFeedbackById(Long id);
    List<Observacion> findObservacionesByFeedbackId(Long idFeedback);
    Observacion saveObservacion(Observacion observacion);
    void deleteObservacionById(Long id);
    List<Entrevista> findEntrevistasByPostulanteId(Long idPostulante);
    List<Entrevista> findEntrevistasWithFeedbackByPostulanteId(Long idPostulante);

    // Add the missing methods
    void actualizarPuntajesIndicadores(Long idEntrevista, List<EntrevistaIndicador> indicadores);
    void actualizarFeedback(Long idEntrevista, Feedback feedback);
}