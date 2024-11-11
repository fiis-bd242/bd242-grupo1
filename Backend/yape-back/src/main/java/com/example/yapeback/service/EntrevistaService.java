package com.example.yapeback.service;

import com.example.yapeback.interfaces.EntrevistaRepository;
import com.example.yapeback.interfaces.ObservacionRepository;
import com.example.yapeback.model.Entrevista;
import com.example.yapeback.model.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrevistaService {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    @Autowired
    private ObservacionRepository observacionRepository;

    public Entrevista save(Entrevista entrevista) {
        return entrevistaRepository.save(entrevista);
    }

    public Entrevista update(Long id, Entrevista entrevista) {
        Entrevista existingEntrevista = entrevistaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrevista not found with id: " + id));
        
        // Set the ID from the path parameter to ensure we're updating the correct record
        entrevista.setId_entrevista(id);
        
        return entrevistaRepository.update(entrevista);
    }

    public Entrevista findById(Long id) {
        Optional<Entrevista> entrevistaOpt = entrevistaRepository.findById(id);
        return entrevistaOpt.orElse(null); // or handle the absence appropriately
    }

    public void deleteById(Long id) {
        // Delete all observations associated with the interview
        observacionRepository.findByEntrevistaId(id).forEach(observacion -> {
            observacionRepository.deleteById(observacion.getId_observacion());
        });
        // Delete the interview
        entrevistaRepository.deleteById(id);
    }

    public List<Observacion> findObservacionesByEntrevistaId(Long idEntrevista) {
        return observacionRepository.findByEntrevistaId(idEntrevista);
    }
}