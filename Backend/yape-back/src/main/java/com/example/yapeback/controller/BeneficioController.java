// src/main/java/com/example/yapeback/controller/BeneficioController.java
package com.example.yapeback.controller;

import com.example.yapeback.model.Beneficio;
import com.example.yapeback.service.BeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficios")
public class BeneficioController {

    @Autowired
    private BeneficioService beneficioService;

    @GetMapping
    public List<Beneficio> getAllBeneficios() {
        return beneficioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficio> getBeneficioById(@PathVariable Long id) {
        return beneficioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Beneficio createBeneficio(@RequestBody Beneficio beneficio) {
        return beneficioService.save(beneficio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficio> updateBeneficio(@PathVariable Long id, @RequestBody Beneficio beneficio) {
        return beneficioService.findById(id)
                .map(existingBeneficio -> {
                    beneficio.setId_beneficio(existingBeneficio.getId_beneficio());
                    return ResponseEntity.ok(beneficioService.save(beneficio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficio(@PathVariable Long id) {
        if (beneficioService.findById(id).isPresent()) {
            beneficioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
