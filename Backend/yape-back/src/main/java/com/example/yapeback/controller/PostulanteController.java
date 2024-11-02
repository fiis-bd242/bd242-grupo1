package com.example.yapeback.controller;

import com.example.yapeback.model.*;
import com.example.yapeback.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {

    @Autowired
    private PostulanteService postulanteService;

    @GetMapping
    public List<Postulante> getAllPostulantes() {
        return postulanteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postulante> getPostulanteById(@PathVariable Long id) {
        Postulante postulante = postulanteService.findById(id);
        return ResponseEntity.ok(postulante);
    }

    @PostMapping
    public ResponseEntity<Postulante> createPostulante(@RequestBody Postulante postulante) {
        try {
            // Establecer valores por defecto si son null
            if (postulante.getPuntaje_general() == null) {
                postulante.setPuntaje_general(0);
            }
            if (postulante.getIdiomas() == null) {
                postulante.setIdiomas(new ArrayList<>());
            }
            if (postulante.getEducaciones() == null) {
                postulante.setEducaciones(new ArrayList<>());
            }
            if (postulante.getHabilidades() == null) {
                postulante.setHabilidades(new ArrayList<>());
            }
            if (postulante.getExperienciasLaborales() == null) {
                postulante.setExperienciasLaborales(new ArrayList<>());
            }

            Postulante newPostulante = postulanteService.save(postulante);
            return ResponseEntity.ok(newPostulante);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postulante> updatePostulante(@PathVariable Long id, @RequestBody Postulante postulante) {
        Postulante existingPostulante = postulanteService.findById(id);
        if (existingPostulante == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Preservar valores existentes si no se proporcionan nuevos
        if (postulante.getNombre() != null) existingPostulante.setNombre(postulante.getNombre());
        if (postulante.getTelefono() != 0) existingPostulante.setTelefono(postulante.getTelefono());
        if (postulante.getId_vacante() != null) existingPostulante.setId_vacante(postulante.getId_vacante());
        if (postulante.getCorreo() != null) existingPostulante.setCorreo(postulante.getCorreo());
        if (postulante.getPuntaje_general() != null) existingPostulante.setPuntaje_general(postulante.getPuntaje_general());
        
        // Actualizar listas
        if (postulante.getIdiomas() != null) {
            postulanteService.deleteIdiomasByPostulanteId(id);
            postulante.getIdiomas().forEach(idioma -> 
                postulanteService.saveIdioma(id, idioma));
        }
        
        if (postulante.getEducaciones() != null) {
            postulanteService.deleteEducacionesByPostulanteId(id);
            postulante.getEducaciones().forEach(educacion -> 
                postulanteService.saveEducacion(id, educacion));
        }
        
        if (postulante.getHabilidades() != null) {
            postulanteService.deleteHabilidadesByPostulanteId(id);
            postulante.getHabilidades().forEach(habilidad -> 
                postulanteService.saveHabilidad(id, habilidad));
        }
        
        if (postulante.getExperienciasLaborales() != null) {
            postulanteService.deleteExperienciasByPostulanteId(id);
            postulante.getExperienciasLaborales().forEach(experiencia -> 
                postulanteService.saveExperiencia(id, experiencia));
        }
        
        Postulante updatedPostulante = postulanteService.save(existingPostulante);
        return ResponseEntity.ok(updatedPostulante);
    }

    @GetMapping("/{id}/entrevistas-hechas")
    public ResponseEntity<List<Entrevista>> getEntrevistasHechasByPostulanteId(@PathVariable Long id) {
        List<Entrevista> entrevistas = postulanteService.findEntrevistasHechasByPostulanteId(id);
        return ResponseEntity.ok(entrevistas); // Always return HTTP 200 with the list (empty or not)
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostulante(@PathVariable Long id) {
        Postulante existingPostulante = postulanteService.findById(id);
        if (existingPostulante == null) {
            return ResponseEntity.notFound().build();
        }
        postulanteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}