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

import java.util.List;
import java.util.Collections;

@Service
public class PostulanteService {

    private final PostulanteRepository postulanteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VacanteRepository vacanteRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostulanteService(PostulanteRepository postulanteRepository,
                             EmpleadoRepository empleadoRepository,
                             VacanteRepository vacanteRepository,
                             JdbcTemplate jdbcTemplate) {
        this.postulanteRepository = postulanteRepository;
        this.empleadoRepository = empleadoRepository;
        this.vacanteRepository = vacanteRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleados != null ? empleados : Collections.emptyList();
    }

    public List<Postulante> findAll() {        List<Postulante> postulantes = postulanteRepository.findAll();        return postulantes != null ? postulantes : Collections.emptyList();    }    public Postulante findById(Long id) {        Postulante postulante = postulanteRepository.findById(id);        return postulante != null ? postulante : new Postulante();    }    public Postulante save(Postulante postulante) {        return postulanteRepository.save(postulante);    }    @Transactional    public void deleteById(Long id) {        postulanteRepository.deleteById(id);    }    public void deleteIdiomasByPostulanteId(Long id) {        postulanteRepository.deleteIdiomasByPostulanteId(id);    }    public void saveIdioma(Long idPostulante, Idioma idioma) {        postulanteRepository.saveIdioma(idPostulante, idioma);    }    public void deleteEducacionesByPostulanteId(Long id) {        postulanteRepository.deleteEducacionesByPostulanteId(id);    }    public void saveEducacion(Long idPostulante, Educacion educacion) {        postulanteRepository.saveEducacion(idPostulante, educacion);    }    public void deleteHabilidadesByPostulanteId(Long id) {        postulanteRepository.deleteHabilidadesByPostulanteId(id);    }    public void saveHabilidad(Long idPostulante, Habilidad habilidad) {        postulanteRepository.saveHabilidad(idPostulante, habilidad);    }    public void deleteExperienciasByPostulanteId(Long id) {        postulanteRepository.deleteExperienciasByPostulanteId(id);    }    public void saveExperiencia(Long idPostulante, ExperienciaLaboral experiencia) {        postulanteRepository.saveExperiencia(idPostulante, experiencia);    }    @Transactional    public void hirePostulante(Long idPostulante) {        Postulante postulante = postulanteRepository.findById(idPostulante);        if (postulante == null) {            throw new RuntimeException("Postulante no encontrado");        }        Empleado empleado = new Empleado();        empleado.setNombre(postulante.getNombre());        empleado.setApellido("Apellido");        empleado.setTelefono(String.valueOf(postulante.getTelefono()));        empleado.setId_puesto(postulante.getId_vacante());        empleadoRepository.save(empleado);        Vacante vacante = vacanteRepository.findById(postulante.getId_vacante());        if (vacante == null) {            throw new RuntimeException("Vacante no encontrada");        }        int nuevaCantidad = vacante.getCantidad() - 1;        vacante.setCantidad(nuevaCantidad);        if (nuevaCantidad <= 0) {            vacante.setEstado("Cerrada");        }        vacanteRepository.save(vacante);    }    public List<Entrevista> findEntrevistasByPostulanteId(Long id) {
        return postulanteRepository.findEntrevistasByPostulanteId(id);
    }
}