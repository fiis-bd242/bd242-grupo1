package com.example.yapeback.controller;

import com.example.yapeback.model.TipoEntrevistaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.yapeback.interfaces.TipoEntrevistaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-entrevista")
public class TipoEntrevistaController {

    @Autowired
    private TipoEntrevistaRepository tipoEntrevistaRepository;

    @GetMapping("/nombres")
    public List<String> getAllNombres() {
        return tipoEntrevistaRepository.findAllNombres();
    }

    @GetMapping("/id-nombres")
    public List<TipoEntrevistaDTO> getAllIdAndNombres() {
        return tipoEntrevistaRepository.findAllIdAndNombres();
    }
}