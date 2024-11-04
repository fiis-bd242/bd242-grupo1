package com.example.yapeback.controller;

import com.example.yapeback.model.Entrevista;
import com.example.yapeback.model.Feedback;
import com.example.yapeback.model.Observacion;
import com.example.yapeback.service.EntrevistaService;
import com.example.yapeback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/entrevistas")
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Entrevista> createEntrevista(@RequestBody Entrevista entrevista) {
        Entrevista newEntrevista = entrevistaService.save(entrevista);

        // Create a new Feedback associated with the new Entrevista
        Feedback feedback = new Feedback();
        feedback.setId_entrevista(newEntrevista.getId_entrevista());
        feedback.setFecha(LocalDate.now());
        feedbackService.save(feedback);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEntrevista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrevista> updateEntrevista(@PathVariable Long id, @RequestBody Entrevista entrevista) {
        Entrevista updatedEntrevista = entrevistaService.update(id, entrevista);
        return ResponseEntity.ok(updatedEntrevista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrevista> getEntrevistaById(@PathVariable Long id) {
        Entrevista entrevista = entrevistaService.findById(id);
        return ResponseEntity.ok(entrevista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrevista(@PathVariable Long id) {
        entrevistaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/observaciones")
    public ResponseEntity<List<Observacion>> getObservacionesByEntrevistaId(@PathVariable Long id) {
        Feedback feedback = feedbackService.findByEntrevistaId(id);
        if (feedback != null) {
            return ResponseEntity.ok(feedback.getObservaciones());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}