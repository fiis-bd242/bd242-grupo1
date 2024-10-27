// src/main/java/com/example/yapeback/service/PuestoService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.PuestoRepository;
import com.example.yapeback.model.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    private final PuestoRepository puestoRepository;

    @Autowired
    public PuestoService(PuestoRepository puestoRepository) {
        this.puestoRepository = puestoRepository;
    }

    public List<Puesto> findAll() {
        return puestoRepository.findAll();
    }

    public Puesto findById(Long id) {
        return puestoRepository.findById(id);
    }

    public Puesto save(Puesto puesto) {
        return puestoRepository.save(puesto);
    }

    public void deleteById(Long id) {
        puestoRepository.deleteById(id);
    }
}