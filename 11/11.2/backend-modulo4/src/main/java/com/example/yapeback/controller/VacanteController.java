// src/main/java/com/example/yapeback/controller/VacanteController.java
package com.example.yapeback.controller;

import com.example.yapeback.model.Convocatoria; // Add this import
import com.example.yapeback.model.Postulante;
import com.example.yapeback.model.Vacante;
import com.example.yapeback.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacantes")
public class VacanteController {

    @Autowired
    private VacanteService vacanteService;

    @GetMapping
    public ResponseEntity<List<Vacante>> getAllVacantes() {
        List<Vacante> vacantes = vacanteService.findAll();
        return new ResponseEntity<>(vacantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacante> getVacanteById(@PathVariable Long id) {
        Vacante vacante = vacanteService.findById(id);
        if (vacante != null) {
            return new ResponseEntity<>(vacante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/convocatorias")
    public ResponseEntity<Convocatoria> createConvocatoria(@PathVariable Long id, @RequestBody Convocatoria convocatoria) {
        Vacante existingVacante = vacanteService.findById(id);
        if (existingVacante != null) {
            convocatoria.setId_vacante(id);
            Convocatoria newConvocatoria = vacanteService.saveConvocatoria(convocatoria);
            return new ResponseEntity<>(newConvocatoria, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Vacante> createVacante(@RequestBody Vacante vacante) {
        Vacante newVacante = vacanteService.save(vacante);
        return new ResponseEntity<>(newVacante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacante> updateVacante(@PathVariable Long id, @RequestBody Vacante vacante) {
        Vacante existingVacante = vacanteService.findById(id);
        if (existingVacante != null) {
            vacante.setId_vacante(id);
            Vacante updatedVacante = vacanteService.save(vacante);
            return new ResponseEntity<>(updatedVacante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacante(@PathVariable Long id) {
        Vacante existingVacante = vacanteService.findById(id);
        if (existingVacante != null) {
            vacanteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/convocatorias/{id}")
    public ResponseEntity<Void> deleteConvocatoria(@PathVariable Long id) {
        vacanteService.deleteConvocatoriaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/postulantes")
    public ResponseEntity<List<Postulante>> getPostulantesByVacanteId(@PathVariable Long id) {
        List<Postulante> postulantes = vacanteService.findPostulantesByVacanteId(id);
        if (postulantes != null) {
            return new ResponseEntity<>(postulantes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/convocatorias/{id}")
    public ResponseEntity<Convocatoria> updateConvocatoria(@PathVariable Long id, @RequestBody Convocatoria convocatoria) {
        Convocatoria existingConvocatoria = vacanteService.findConvocatoriaById(id);
        if (existingConvocatoria != null) {
            convocatoria.setId_convocatoria(id);
            Convocatoria updatedConvocatoria = vacanteService.saveConvocatoria(convocatoria);
            return new ResponseEntity<>(updatedConvocatoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}