package com.example.yapeback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yapeback.interfaces.AudienciaInterface;
import com.example.yapeback.model.Audiencia;

@RestController
@RequestMapping("/api/audiencia")
public class AudienciaController {

    private final AudienciaInterface audienciaService;

    public AudienciaController(AudienciaInterface audienciaService) {
        this.audienciaService = audienciaService;
    }

    // Método para buscar o crear una audiencia
    @PostMapping("/buscar-o-crear")
    public ResponseEntity<Audiencia> buscarOCrearAudiencia(@RequestBody Audiencia audiencia) {
        Audiencia resultado = audienciaService.buscarOCrearAudiencia(audiencia);
        return ResponseEntity.ok(resultado);
    }
}
