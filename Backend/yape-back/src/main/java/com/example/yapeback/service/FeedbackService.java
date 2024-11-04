package com.example.yapeback.service;

import com.example.yapeback.interfaces.FeedbackRepository;
import com.example.yapeback.interfaces.ObservacionRepository;
import com.example.yapeback.model.Feedback;
import com.example.yapeback.model.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ObservacionRepository observacionRepository;

    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback findByEntrevistaId(Long idEntrevista) {
        Feedback feedback = feedbackRepository.findByEntrevistaId(idEntrevista).orElse(null);
        if (feedback != null) {
            List<Observacion> observaciones = observacionRepository.findByFeedbackId(feedback.getId_feedback());
            feedback.setObservaciones(observaciones);
        }
        return feedback;
    }
}