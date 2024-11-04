package com.example.yapeback.controller;

import com.example.yapeback.model.*;
import com.example.yapeback.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrevistas")
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

    @GetMapping
    public List<Entrevista> getAllEntrevistas() {
        return entrevistaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrevista> getEntrevistaById(@PathVariable Long id) {
        Entrevista entrevista = entrevistaService.findById(id);
        return ResponseEntity.ok(entrevista);
    }

    @PostMapping
    public ResponseEntity<Entrevista> createEntrevista(@RequestBody Entrevista entrevista) {
        try {
            Entrevista newEntrevista = entrevistaService.createEntrevista(entrevista);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEntrevista);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrevista> updateEntrevista(@PathVariable Long id, @RequestBody Entrevista entrevista) {
        try {
            Entrevista updatedEntrevista = entrevistaService.updateEntrevista(id, entrevista);
            return ResponseEntity.ok(updatedEntrevista);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/puntajes")
    public ResponseEntity<Void> updatePuntajes(@PathVariable Long id, @RequestBody List<PuntajeIndicador> puntajes) {
        try {
            entrevistaService.updatePuntajesIndicadores(id, puntajes);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/observaciones")
    public ResponseEntity<Void> addObservacion(@PathVariable Long id, @RequestBody Observacion observacion) {
        try {
            Entrevista entrevista = entrevistaService.getEntrevistaCompleta(id);
            entrevistaService.addObservacion(entrevista.getId_feedback(), observacion);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrevista(@PathVariable Long id) {
        try {
            entrevistaService.deleteEntrevista(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/completa")
    public ResponseEntity<Entrevista> getEntrevistaCompleta(@PathVariable Long id) {
        Entrevista entrevista = entrevistaService.getEntrevistaCompleta(id);
        if (entrevista != null) {
            return ResponseEntity.ok(entrevista);
        }
        return ResponseEntity.notFound().build();
    }
}