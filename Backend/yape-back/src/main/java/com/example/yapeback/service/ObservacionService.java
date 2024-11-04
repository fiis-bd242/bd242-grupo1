package com.example.yapeback.service;

import com.example.yapeback.interfaces.ObservacionRepository;
import com.example.yapeback.model.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObservacionService {

    @Autowired
    private ObservacionRepository observacionRepository;

    public Observacion save(Observacion observacion) {
        return observacionRepository.save(observacion);
    }

    public Observacion update(Long id, Observacion observacion) {
        Optional<Observacion> existingObservacionOpt = observacionRepository.findById(id);
        if (existingObservacionOpt.isPresent()) {
            Observacion existingObservacion = existingObservacionOpt.get();
            existingObservacion.setNombre(observacion.getNombre());
            existingObservacion.setDescripcion(observacion.getDescripcion());
            return observacionRepository.save(existingObservacion);
        } else {
            // Handle the case where the Observacion is not found
            return null; // or throw an appropriate exception
        }
    }

    public Observacion findById(Long id) {
        Optional<Observacion> observacionOpt = observacionRepository.findById(id);
        return observacionOpt.orElse(null); // or handle the absence appropriately
    }

    public void deleteById(Long id) {
        observacionRepository.deleteById(id);
    }
}