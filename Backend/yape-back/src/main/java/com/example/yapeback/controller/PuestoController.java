// src/main/java/com/example/yapeback/controller/PuestoController.java
package com.example.yapeback.controller;

import com.example.yapeback.model.Puesto;
import com.example.yapeback.service.PuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puestos")
public class PuestoController {

    @Autowired
    private PuestoService puestoService;

    @GetMapping
    public ResponseEntity<List<Puesto>> getAllPuestos() {
        List<Puesto> puestos = puestoService.findAll();
        return new ResponseEntity<>(puestos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puesto> getPuestoById(@PathVariable Long id) {
        Puesto puesto = puestoService.findById(id);
        if (puesto != null) {
            return new ResponseEntity<>(puesto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Puesto> createPuesto(@RequestBody Puesto puesto) {
        Puesto newPuesto = puestoService.save(puesto);
        return new ResponseEntity<>(newPuesto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Puesto> updatePuesto(@PathVariable Long id, @RequestBody Puesto puesto) {
        Puesto existingPuesto = puestoService.findById(id);
        if (existingPuesto != null) {
            puesto.setId_puesto(id);
            Puesto updatedPuesto = puestoService.save(puesto);
            return new ResponseEntity<>(updatedPuesto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuesto(@PathVariable Long id) {
        Puesto existingPuesto = puestoService.findById(id);
        if (existingPuesto != null) {
            puestoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}