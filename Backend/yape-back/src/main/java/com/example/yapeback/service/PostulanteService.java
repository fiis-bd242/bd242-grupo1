// src/main/java/com/example/yapeback/service/PostulanteService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.EmpleadoRepository;
import com.example.yapeback.interfaces.PostulanteRepository;
import com.example.yapeback.interfaces.VacanteRepository;
import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostulanteService {

    private final PostulanteRepository postulanteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VacanteRepository vacanteRepository;

    @Autowired
    public PostulanteService(PostulanteRepository postulanteRepository,
                             EmpleadoRepository empleadoRepository,
                             VacanteRepository vacanteRepository) {
        this.postulanteRepository = postulanteRepository;
        this.empleadoRepository = empleadoRepository;
        this.vacanteRepository = vacanteRepository;
    }

    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    public List<Feedback> findFeedbacksByPostulanteId(Long idPostulante) {
        return postulanteRepository.findFeedbacksByPostulanteId(idPostulante);
    }

    public List<Postulante> findAll() {
        return postulanteRepository.findAll();
    }

    public Postulante findById(Long id) {
        return postulanteRepository.findById(id);
    }

    public Postulante save(Postulante postulante) {
        return postulanteRepository.save(postulante);
    }

    @Transactional
    public void deleteById(Long id) {
        postulanteRepository.deleteById(id);
    }

    public void deleteIdiomasByPostulanteId(Long id) {
        postulanteRepository.deleteIdiomasByPostulanteId(id);
    }

    public void saveIdioma(Long idPostulante, Idioma idioma) {
        postulanteRepository.saveIdioma(idPostulante, idioma);
    }

    public void deleteEducacionesByPostulanteId(Long id) {
        postulanteRepository.deleteEducacionesByPostulanteId(id);
    }

    public void saveEducacion(Long idPostulante, Educacion educacion) {
        postulanteRepository.saveEducacion(idPostulante, educacion);
    }

    public void deleteHabilidadesByPostulanteId(Long id) {
        postulanteRepository.deleteHabilidadesByPostulanteId(id);
    }

    public void saveHabilidad(Long idPostulante, Habilidad habilidad) {
        postulanteRepository.saveHabilidad(idPostulante, habilidad);
    }

    public void deleteExperienciasByPostulanteId(Long id) {
        postulanteRepository.deleteExperienciasByPostulanteId(id);
    }

    public void saveExperiencia(Long idPostulante, ExperienciaLaboral experiencia) {
        postulanteRepository.saveExperiencia(idPostulante, experiencia);
    }

    public String generateSlogan(Long id) {
        Postulante postulante = postulanteRepository.findById(id);
        if (postulante == null) {
            return "Postulante no encontrado";
        }

        List<Feedback> feedbacks = postulanteRepository.findFeedbacksByPostulanteId(id);
        StringBuilder summary = new StringBuilder();
        for (Feedback feedback : feedbacks) {
            summary.append(feedback.getDescripcion()).append(" ");
        }

        return "Excelente candidato: " + summary.toString().trim();
    }

    @Transactional
    public void hirePostulante(Long idPostulante) {
        Postulante postulante = postulanteRepository.findById(idPostulante);
        if (postulante == null) {
            throw new RuntimeException("Postulante no encontrado");
        }

        Empleado empleado = new Empleado();
        empleado.setNombre(postulante.getNombre());
        empleado.setApellido("Apellido");
        empleado.setTelefono(String.valueOf(postulante.getTelefono()));
        empleado.setId_puesto(postulante.getId_vacante());
        empleadoRepository.save(empleado);

        Vacante vacante = vacanteRepository.findById(postulante.getId_vacante());
        if (vacante == null) {
            throw new RuntimeException("Vacante no encontrada");
        }

        int nuevaCantidad = vacante.getCantidad() - 1;
        vacante.setCantidad(nuevaCantidad);

        if (nuevaCantidad <= 0) {
            vacante.setEstado("Cerrada");
        }

        vacanteRepository.save(vacante);
    }

    public List<Entrevista> findEntrevistasHechasByPostulanteId(Long idPostulante) {
        return postulanteRepository.findEntrevistasWithFeedbackByPostulanteId(idPostulante);
    }

    public List<TipoEntrevista> findAllTiposEntrevista() {
        return postulanteRepository.findAllTiposEntrevista();
    }

    public List<EntrevistaIndicador> findIndicadoresByEntrevistaId(Long id) {
        return postulanteRepository.findIndicadoresByEntrevistaId(id);
    }

    public Entrevista createEntrevistaWithIndicadores(Entrevista entrevista) {
        return postulanteRepository.saveEntrevistaWithIndicadores(entrevista);
    }

    public Entrevista updateEntrevista(Long id, EntrevistaUpdateDTO dto) {
        return postulanteRepository.updateEntrevista(id, dto);
    }

    public void updateIndicadoresEntrevista(Long id, List<EntrevistaIndicador> indicadores) {
        postulanteRepository.updateIndicadoresEntrevista(id, indicadores);
    }

    public List<Entrevista> findEntrevistasByPostulanteId(Long idPostulante) {
        return postulanteRepository.findEntrevistasByPostulanteId(idPostulante);
    }
}