// src/main/java/com/example/yapeback/service/OfertaLaboralService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.OfertaLaboralRepository;
import com.example.yapeback.model.OfertaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaLaboralService {

    @Autowired
    private OfertaLaboralRepository ofertaLaboralRepository;

    public List<OfertaLaboral> findAll() {
        return ofertaLaboralRepository.findAll();
    }

    public Optional<OfertaLaboral> findById(Long id) {
        return Optional.ofNullable(ofertaLaboralRepository.findById(id));
    }

    public OfertaLaboral save(OfertaLaboral ofertaLaboral) {
        return ofertaLaboralRepository.save(ofertaLaboral);
    }

    public void deleteById(Long id) {
        ofertaLaboralRepository.deleteById(id);
    }
}