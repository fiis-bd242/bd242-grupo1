package com.example.yapeback.controller;

import com.example.yapeback.model.Observacion;
import com.example.yapeback.service.ObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/observaciones")
public class ObservacionesController {

    @Autowired
    private ObservacionService observacionService;

    @PostMapping
    public ResponseEntity<Observacion> createObservacion(@RequestBody Observacion observacion) {
        Observacion newObservacion = observacionService.save(observacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newObservacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Observacion> updateObservacion(@PathVariable Long id, @RequestBody Observacion observacion) {
        Observacion updatedObservacion = observacionService.update(id, observacion);
        return ResponseEntity.ok(updatedObservacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observacion> getObservacionById(@PathVariable Long id) {
        Observacion observacion = observacionService.findById(id);
        return ResponseEntity.ok(observacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservacion(@PathVariable Long id) {
        observacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}