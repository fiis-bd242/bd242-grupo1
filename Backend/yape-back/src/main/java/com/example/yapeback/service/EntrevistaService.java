package com.example.yapeback.service;

import com.example.yapeback.interfaces.EntrevistaRepository;
import com.example.yapeback.interfaces.FeedbackRepository;
import com.example.yapeback.interfaces.ObservacionRepository;
import com.example.yapeback.model.Entrevista;
import com.example.yapeback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrevistaService {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

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
        // Find the feedback associated with the interview
        Optional<Feedback> feedbackOpt = feedbackRepository.findByEntrevistaId(id);
        if (feedbackOpt.isPresent()) {
            Feedback feedback = feedbackOpt.get();
            // Delete all observations associated with the feedback
            observacionRepository.findByFeedbackId(feedback.getId_feedback()).forEach(observacion -> {
                observacionRepository.deleteById(observacion.getId_observacion());
            });
            // Delete the feedback
            feedbackRepository.deleteById(feedback.getId_feedback());
        }
        // Delete the interview
        entrevistaRepository.deleteById(id);
    }
}