// src/main/java/com/example/yapeback/controller/PostulanteController.java
package com.example.yapeback.controller;

import com.example.yapeback.model.Postulante;
import com.example.yapeback.model.Feedback;
import com.example.yapeback.model.Observacion;
import com.example.yapeback.interfaces.PostulanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.yapeback.model.Entrevista;
import com.example.yapeback.model.EntrevistaIndicador;
import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {

    @Autowired
    private PostulanteRepository postulanteRepository;

    @GetMapping
    public List<Postulante> getAllPostulantes() {
        return postulanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postulante> getPostulanteById(@PathVariable Long id) {
        Postulante postulante = postulanteRepository.findById(id);
        if (postulante == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postulante);
    }

    @PostMapping
    public Postulante createPostulante(@RequestBody Postulante postulante) {
        return postulanteRepository.save(postulante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postulante> updatePostulante(@PathVariable Long id, @RequestBody Postulante postulanteDetails) {
        Postulante postulante = postulanteRepository.findById(id);
        if (postulante == null) {
            return ResponseEntity.notFound().build();
        }
        postulante.setNombre(postulanteDetails.getNombre());
        postulante.setTelefono(postulanteDetails.getTelefono());
        postulante.setId_vacante(postulanteDetails.getId_vacante());
        postulante.setCorreo(postulanteDetails.getCorreo());
        postulante.setIdiomas(postulanteDetails.getIdiomas());
        postulante.setEducaciones(postulanteDetails.getEducaciones());
        postulante.setHabilidades(postulanteDetails.getHabilidades());
        postulante.setExperienciasLaborales(postulanteDetails.getExperienciasLaborales());
        postulante.setOfertaLaboral(postulanteDetails.getOfertaLaboral());
        Postulante updatedPostulante = postulanteRepository.save(postulante);
        return ResponseEntity.ok(updatedPostulante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostulante(@PathVariable Long id) {
        Postulante postulante = postulanteRepository.findById(id);
        if (postulante == null) {
            return ResponseEntity.notFound().build();
        }
        postulanteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/entrevistas/{id}/feedback")
    public Feedback getFeedbackByEntrevistaId(@PathVariable Long id) {
        return postulanteRepository.findFeedbackByEntrevistaId(id);
    }

    @PostMapping("/entrevistas/{id}/feedback")
    public Feedback createFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        feedback.setId_entrevista(id);
        return postulanteRepository.saveFeedback(feedback);
    }

    @PutMapping("/feedback/{id}")
    public ResponseEntity<Feedback> modifyFeedback(@PathVariable Long id, @RequestBody Feedback feedbackDetails) {
        Feedback feedback = postulanteRepository.findFeedbackByEntrevistaId(id);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        feedback.setObservaciones(feedbackDetails.getObservaciones());
        Feedback updatedFeedback = postulanteRepository.saveFeedback(feedback);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        postulanteRepository.deleteFeedbackById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/feedback/{id}/observaciones")
    public List<Observacion> getObservacionesByFeedbackId(@PathVariable Long id) {
        return postulanteRepository.findObservacionesByFeedbackId(id);
    }

    @PostMapping("/feedback/{id}/observaciones")
    public Observacion createObservacion(@PathVariable Long id, @RequestBody Observacion observacion) {
        observacion.setId_feedback(id);
        return postulanteRepository.saveObservacion(observacion);
    }

    @PutMapping("/observaciones/{id}")
    public ResponseEntity<Observacion> updateObservacion(@PathVariable Long id, @RequestBody Observacion observacionDetails) {
        Observacion observacion = postulanteRepository.findObservacionesByFeedbackId(id).stream()
                .filter(o -> o.getId_observacion().equals(id))
                .findFirst()
                .orElse(null);
        if (observacion == null) {
            return ResponseEntity.notFound().build();
        }
        observacion.setId_categoria(observacionDetails.getId_categoria());
        observacion.setDescripcion(observacionDetails.getDescripcion());
        Observacion updatedObservacion = postulanteRepository.saveObservacion(observacion);
        return ResponseEntity.ok(updatedObservacion);
    }

    @DeleteMapping("/observaciones/{id}")
    public ResponseEntity<Void> deleteObservacion(@PathVariable Long id) {
        postulanteRepository.deleteObservacionById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/entrevistas")
    public ResponseEntity<List<Entrevista>> getEntrevistasByPostulanteId(@PathVariable Long id) {
        List<Entrevista> entrevistas = postulanteRepository.findEntrevistasByPostulanteId(id);
        if (entrevistas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/{id}/entrevistas/feedback")
    public ResponseEntity<List<Entrevista>> getEntrevistasWithFeedbackByPostulanteId(@PathVariable Long id) {
        List<Entrevista> entrevistas = postulanteRepository.findEntrevistasWithFeedbackByPostulanteId(id);
        if (entrevistas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entrevistas);
    }

    @PutMapping("/entrevistas/{id}/indicadores")
    public ResponseEntity<Void> updatePuntajesIndicadores(@PathVariable Long id, @RequestBody List<EntrevistaIndicador> indicadores) {
        postulanteRepository.actualizarPuntajesIndicadores(id, indicadores);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/entrevistas/{id}/feedback")
    public ResponseEntity<Void> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        postulanteRepository.actualizarFeedback(id, feedback);
        return ResponseEntity.noContent().build();
    }
}