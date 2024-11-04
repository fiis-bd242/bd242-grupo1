package com.example.yapeback.service;

import com.example.yapeback.model.*;
import com.example.yapeback.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EntrevistaService {

    private final EntrevistaRepository entrevistaRepository;
    private final TipoEntrevistaRepository tipoEntrevistaRepository;
    private final FeedbackRepository feedbackRepository;
    private final PuntajeIndicadorRepository puntajeIndicadorRepository;
    private final ObservacionRepository observacionRepository;
    private final IndicadorRepository indicadorRepository;

    @Autowired
    public EntrevistaService(EntrevistaRepository entrevistaRepository,
                             TipoEntrevistaRepository tipoEntrevistaRepository,
                             FeedbackRepository feedbackRepository,
                             PuntajeIndicadorRepository puntajeIndicadorRepository,
                             ObservacionRepository observacionRepository
                             , IndicadorRepository indicadorRepository) {
        this.entrevistaRepository = entrevistaRepository;
        this.tipoEntrevistaRepository = tipoEntrevistaRepository;
        this.feedbackRepository = feedbackRepository;
        this.puntajeIndicadorRepository = puntajeIndicadorRepository;
        this.observacionRepository = observacionRepository;
        this.indicadorRepository = indicadorRepository;
    }

    @Transactional
    public Entrevista createEntrevista(Entrevista entrevista) {
        if (!tipoEntrevistaRepository.existsById(entrevista.getId_tipo_entrevista())) {
            throw new RuntimeException("Tipo de entrevista no existe");
        }

        Feedback feedback = new Feedback();
        feedback.setFecha(entrevista.getFecha());
        feedback = feedbackRepository.save(feedback);
        entrevista.setId_feedback(feedback.getId_feedback());

        entrevista.setPuntaje_general(0);
        entrevista = entrevistaRepository.save(entrevista);

        List<Indicador> indicadores = tipoEntrevistaRepository.findIndicadoresByTipoId(entrevista.getId_tipo_entrevista());
        for (Indicador indicador : indicadores) {
            PuntajeIndicador puntaje = new PuntajeIndicador();
            puntaje.setId_entrevista(entrevista.getId_entrevista());
            puntaje.setId_indicador(indicador.getId_indicador());
            puntaje.setPuntaje(0);
            puntajeIndicadorRepository.save(puntaje);
        }

        return entrevista;
    }

    @Transactional
    public Entrevista updateEntrevista(Long id, Entrevista entrevista) {
        Entrevista existingEntrevista = entrevistaRepository.findById(id);
        if (existingEntrevista == null) {
            throw new RuntimeException("Entrevista no encontrada");
        }

        entrevista.setId_tipo_entrevista(existingEntrevista.getId_tipo_entrevista());
        return entrevistaRepository.update(id, entrevista);
    }

    @Transactional
    public void updatePuntajesIndicadores(Long idEntrevista, List<PuntajeIndicador> puntajes) {
        for (PuntajeIndicador puntaje : puntajes) {
            puntajeIndicadorRepository.update(idEntrevista, puntaje);
        }
        updatePuntajeGeneral(idEntrevista);
    }

    @Transactional
    public void updatePuntajeGeneral(Long idEntrevista) {
        List<PuntajeIndicador> puntajes = puntajeIndicadorRepository.findByEntrevistaId(idEntrevista);
        int puntajeTotal = puntajes.stream().mapToInt(PuntajeIndicador::getPuntaje).sum();

        Entrevista entrevista = entrevistaRepository.findById(idEntrevista);
        entrevista.setPuntaje_general(puntajeTotal);

        // Convertir la lista de PuntajeIndicador a EntrevistaIndicador
        List<EntrevistaIndicador> entrevistaIndicadores = puntajes.stream()
                .map(puntaje -> new EntrevistaIndicador(puntaje.getId_indicador(), puntaje.getPuntaje()))
                .collect(Collectors.toList());
        entrevista.setIndicadores(entrevistaIndicadores);

        entrevistaRepository.update(idEntrevista, entrevista);
    }

    @Transactional
    public void addObservacion(Long idFeedback, Observacion observacion) {
        if (!observacionRepository.existsCategoriaById(observacion.getId_categoria())) {
            throw new RuntimeException("Categoría de observación no existe");
        }
        observacion.setId_feedback(idFeedback);
        observacionRepository.save(observacion);
    }

    @Transactional
    public void deleteEntrevista(Long id) {
        Entrevista entrevista = entrevistaRepository.findById(id);
        if (entrevista != null) {
            puntajeIndicadorRepository.deleteByEntrevistaId(id);
            observacionRepository.deleteByFeedbackId(entrevista.getId_feedback());
            feedbackRepository.deleteById(entrevista.getId_feedback());
            entrevistaRepository.deleteById(id);
        }
    }

    public Entrevista getEntrevistaCompleta(Long id) {
        Entrevista entrevista = entrevistaRepository.findById(id);
        if (entrevista != null) {
            List<PuntajeIndicador> puntajes = puntajeIndicadorRepository.findByEntrevistaId(id);
            List<EntrevistaIndicador> entrevistaIndicadores = puntajes.stream()
                    .map(puntaje -> new EntrevistaIndicador(puntaje.getId_indicador(), puntaje.getPuntaje()))
                    .collect(Collectors.toList());
            entrevista.setIndicadores(entrevistaIndicadores);

            Feedback feedback = feedbackRepository.findById(entrevista.getId_feedback());
            if (feedback != null) {
                feedback.setObservaciones(observacionRepository.findByFeedbackId(feedback.getId_feedback()));
                entrevista.setFeedback(feedback);
            }
        }
        return entrevista;
    }

    public List<Entrevista> findAll() {
        List<Entrevista> entrevistas = entrevistaRepository.findAll();
        for (Entrevista entrevista : entrevistas) {
            populateRelatedData(entrevista);
        }
        return entrevistas;
    }

    public Entrevista findById(Long id) {
        Entrevista entrevista = entrevistaRepository.findById(id);
        if (entrevista != null) {
            populateRelatedData(entrevista);
        }
        return entrevista;
    }

    private void populateRelatedData(Entrevista entrevista) {
        List<PuntajeIndicador> puntajes = puntajeIndicadorRepository.findByEntrevistaId(entrevista.getId_entrevista());
        List<EntrevistaIndicador> entrevistaIndicadores = puntajes.stream()
                .map(puntaje -> {
                    EntrevistaIndicador indicador = new EntrevistaIndicador();
                    indicador.setId_indicador(puntaje.getId_indicador());
                    indicador.setPuntaje(puntaje.getPuntaje());
                    Indicador indicadorEntity = indicadorRepository.findById(puntaje.getId_indicador());
                    if (indicadorEntity != null) {
                        indicador.setNombre(indicadorEntity.getNombre());
                    }
                    return indicador;
                })
                .collect(Collectors.toList());
        entrevista.setIndicadores(entrevistaIndicadores);

        Feedback feedback = feedbackRepository.findById(entrevista.getId_feedback());
        if (feedback != null) {
            feedback.setObservaciones(observacionRepository.findByFeedbackId(feedback.getId_feedback()));
            entrevista.setFeedback(feedback);
        }
    }

    public Entrevista save(Entrevista entrevista) {
        return entrevistaRepository.save(entrevista);
    }

    public Entrevista update(Long id, Entrevista entrevista) {
        return entrevistaRepository.update(id, entrevista);
    }

    public void deleteById(Long id) {
        entrevistaRepository.deleteById(id);
    }
}