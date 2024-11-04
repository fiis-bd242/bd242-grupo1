package com.example.yapeback.interfaces;

import com.example.yapeback.model.Observacion;
import java.util.List;

public interface ObservacionRepository {
    void save(Observacion observacion);
    List<Observacion> findByFeedbackId(Long idFeedback);
    void deleteByFeedbackId(Long idFeedback);
    boolean existsCategoriaById(Long idCategoria);
}