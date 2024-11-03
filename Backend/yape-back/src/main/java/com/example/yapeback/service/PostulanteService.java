// src/main/java/com/example/yapeback/service/PostulanteService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.EmpleadoRepository;
import com.example.yapeback.interfaces.PostulanteRepository;
import com.example.yapeback.interfaces.VacanteRepository;
import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.yapeback.repository.EntrevistaWithFeedbackRowMapper;

import java.util.List;
import java.util.Collections;

@Service
public class PostulanteService {

    private final PostulanteRepository postulanteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VacanteRepository vacanteRepository;
    private final JdbcTemplate jdbcTemplate; // Añade este campo

    @Autowired
    public PostulanteService(PostulanteRepository postulanteRepository,
                             EmpleadoRepository empleadoRepository,
                             VacanteRepository vacanteRepository,
                             JdbcTemplate jdbcTemplate) { // Añade JdbcTemplate en el constructor
        this.postulanteRepository = postulanteRepository;
        this.empleadoRepository = empleadoRepository;
        this.vacanteRepository = vacanteRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleados != null ? empleados : Collections.emptyList();
    }

    public List<Feedback> findFeedbacksByPostulanteId(Long idPostulante) {
        List<Feedback> feedbacks = postulanteRepository.findFeedbacksByPostulanteId(idPostulante);
        return feedbacks != null ? feedbacks : Collections.emptyList();
    }

    public List<Postulante> findAll() {
        List<Postulante> postulantes = postulanteRepository.findAll();
        return postulantes != null ? postulantes : Collections.emptyList();
    }

    public Postulante findById(Long id) {
        Postulante postulante = postulanteRepository.findById(id);
        return postulante != null ? postulante : new Postulante();
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
            for (Observacion observacion : feedback.getObservaciones()) {
                summary.append(observacion.getDescripcion()).append(" ");
            }
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
        List<Entrevista> entrevistas = postulanteRepository.findEntrevistasWithFeedbackByPostulanteId(idPostulante);
        return entrevistas != null ? entrevistas : Collections.emptyList();
    }

    public List<TipoEntrevista> findAllTiposEntrevista() {
        List<TipoEntrevista> tiposEntrevista = postulanteRepository.findAllTiposEntrevista();
        return tiposEntrevista != null ? tiposEntrevista : Collections.emptyList();
    }

    public List<EntrevistaIndicador> findIndicadoresByEntrevistaId(Long id) {
        List<EntrevistaIndicador> indicadores = postulanteRepository.findIndicadoresByEntrevistaId(id);
        return indicadores != null ? indicadores : Collections.emptyList();
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
        List<Entrevista> entrevistas = postulanteRepository.findEntrevistasByPostulanteId(idPostulante);
        return entrevistas != null ? entrevistas : Collections.emptyList();
    }

    public List<Entrevista> findEntrevistasWithFeedbackByPostulanteId(Long postulanteId) {
        String sql = "SELECT e.*, f.fecha AS feedback_fecha FROM Entrevista e " +
                "JOIN Feedback f ON e.id_feedback = f.id_feedback " +
                "WHERE e.id_postulante = ?";
        List<Entrevista> entrevistas = jdbcTemplate.query(sql, new Object[]{postulanteId}, new EntrevistaWithFeedbackRowMapper(postulanteRepository));
        return entrevistas != null ? entrevistas : Collections.emptyList();
    }
}