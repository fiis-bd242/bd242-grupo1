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
import java.util.stream.Collectors;

@Service
public class PostulanteService {

    private final PostulanteRepository postulanteRepository;
    private final EmpleadoRepository empleadoRepository; // Assume this repository exists
    private final VacanteRepository vacanteRepository;   // Assume this repository exists

    @Autowired
    public PostulanteService(PostulanteRepository postulanteRepository,
                             EmpleadoRepository empleadoRepository,
                             VacanteRepository vacanteRepository) {
        this.postulanteRepository = postulanteRepository;
        this.empleadoRepository = empleadoRepository;
        this.vacanteRepository = vacanteRepository;
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

    @Transactional // Agregar esta anotación para asegurar que todas las operaciones se realizan en una transacción
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
            throw new RuntimeException("Postulante no encontrado.");
        }

        List<Feedback> feedbacks = postulanteRepository.findFeedbacksByPostulanteId(id);
        StringBuilder summary = new StringBuilder();
        for (Feedback feedback : feedbacks) {
            for (Observacion observacion : feedback.getObservaciones()) {
                summary.append(observacion.getDescripcion()).append(" ");
            }
        }

        // Simple slogan generation based on summary
        String slogan = "Excelente candidato: " + summary.toString().trim();
        return slogan;
    }

    @Transactional
    public void hirePostulante(Long idPostulante) {
        Postulante postulante = postulanteRepository.findById(idPostulante);
        if (postulante == null) {
            throw new RuntimeException("Postulante no encontrado.");
        }

        // Create new Empleado based on Postulante details
        Empleado empleado = new Empleado();
        empleado.setNombre(postulante.getNombre());
        empleado.setApellido("Apellido"); // Set appropriately
        empleado.setTelefono(String.valueOf(postulante.getTelefono()));
        empleado.setId_puesto(postulante.getId_vacante()); // Assuming mapping
        // ... set other necessary fields ...
        empleadoRepository.save(empleado);

        // Decrement 'cantidad' in Vacante
        Vacante vacante = vacanteRepository.findById(postulante.getId_vacante());
        if (vacante == null) {
            throw new RuntimeException("Vacante no encontrada.");
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
}