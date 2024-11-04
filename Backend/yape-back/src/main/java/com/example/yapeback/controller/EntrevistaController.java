package com.example.yapeback.controller;

import com.example.yapeback.model.Entrevista;
import com.example.yapeback.model.Observacion;
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

    @PostMapping
    public ResponseEntity<Entrevista> createEntrevista(@RequestBody Entrevista entrevista) {
        Entrevista newEntrevista = entrevistaService.save(entrevista);
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
        List<Observacion> observaciones = entrevistaService.findObservacionesByEntrevistaId(id);
        return ResponseEntity.ok(observaciones);
    }
}