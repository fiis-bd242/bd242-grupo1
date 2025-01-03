package com.example.yapeback.controller;

import com.example.yapeback.model.OfertaLaboral;
import com.example.yapeback.model.Beneficio;
import com.example.yapeback.service.OfertaLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas-laborales")
public class OfertaLaboralController {

    @Autowired
    private OfertaLaboralService ofertaLaboralService;

    @GetMapping
    public List<OfertaLaboral> getAllOfertasLaborales() {
        return ofertaLaboralService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaLaboral> getOfertaLaboralById(@PathVariable Long id) {
        return ofertaLaboralService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OfertaLaboral createOfertaLaboral(@RequestBody OfertaLaboral ofertaLaboral) {
        return ofertaLaboralService.save(ofertaLaboral);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertaLaboral> updateOfertaLaboral(@PathVariable Long id, @RequestBody OfertaLaboral ofertaLaboral) {
        return ofertaLaboralService.findById(id)
                .map(existingOferta -> {
                    ofertaLaboral.setId_oferta(existingOferta.getId_oferta());
                    return ResponseEntity.ok(ofertaLaboralService.save(ofertaLaboral));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfertaLaboral(@PathVariable Long id) {
        if (ofertaLaboralService.findById(id).isPresent()) {
            ofertaLaboralService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/beneficios")
    public ResponseEntity<OfertaLaboral> assignBeneficios(@PathVariable Long id, @RequestBody List<Beneficio> beneficios) {
        OfertaLaboral updatedOferta = ofertaLaboralService.assignBeneficios(id, beneficios);
        if (updatedOferta != null) {
            return ResponseEntity.ok(updatedOferta);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/beneficios")
    public ResponseEntity<OfertaLaboral> updateBeneficios(@PathVariable Long id, @RequestBody List<Beneficio> beneficios) {
        OfertaLaboral updatedOferta = ofertaLaboralService.updateBeneficios(id, beneficios);
        if (updatedOferta != null) {
            return ResponseEntity.ok(updatedOferta);
        }
        return ResponseEntity.notFound().build();
    }
}