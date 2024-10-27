// src/main/java/com/example/yapeback/service/PostulanteService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.PostulanteRepository;
import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulanteService {

    private final PostulanteRepository postulanteRepository;

    @Autowired
    public PostulanteService(PostulanteRepository postulanteRepository) {
        this.postulanteRepository = postulanteRepository;
    }

    public List<Postulante> findAll() {
        return postulanteRepository.findAll();
    }

    public Postulante findById(Long id) {
        return postulanteRepository.findById(id);
    }

    public Postulante save(Postulante postulante) {
        return postulanteRepository.save(postulante);
    }

    public void deleteById(Long id) {
        postulanteRepository.deleteById(id);
    }
}