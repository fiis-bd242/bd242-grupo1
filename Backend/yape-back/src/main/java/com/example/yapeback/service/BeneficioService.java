// src/main/java/com/example/yapeback/service/BeneficioService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.BeneficioRepository;
import com.example.yapeback.model.Beneficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository beneficioRepository;

    public List<Beneficio> findAll() {
        return beneficioRepository.findAll();
    }

    public Optional<Beneficio> findById(Long id) {
        return Optional.ofNullable(beneficioRepository.findById(id));
    }

    public Beneficio save(Beneficio beneficio) {
        return beneficioRepository.save(beneficio);
    }

    public void deleteById(Long id) {
        beneficioRepository.deleteById(id);
    }
}