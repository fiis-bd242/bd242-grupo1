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
        Optional<Entrevista> existingEntrevistaOpt = entrevistaRepository.findById(id);
        if (existingEntrevistaOpt.isPresent()) {
            Entrevista existingEntrevista = existingEntrevistaOpt.get();
            existingEntrevista.setEstado(entrevista.getEstado());
            existingEntrevista.setFecha(entrevista.getFecha());
            existingEntrevista.setPuntaje_general(entrevista.getPuntaje_general());
            existingEntrevista.setId_postulante(entrevista.getId_postulante());
            existingEntrevista.setId_empleado(entrevista.getId_empleado());
            existingEntrevista.setTipo_entrevista(entrevista.getTipo_entrevista());
            return entrevistaRepository.save(existingEntrevista);
        } else {
            // Handle the case where the Entrevista is not found
            return null; // or throw an appropriate exception
        }
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