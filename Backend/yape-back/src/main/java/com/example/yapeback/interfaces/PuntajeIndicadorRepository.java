package com.example.yapeback.interfaces;

import com.example.yapeback.model.PuntajeIndicador;
import java.util.List;

public interface PuntajeIndicadorRepository {
    void save(PuntajeIndicador puntaje);
    void update(Long idEntrevista, PuntajeIndicador puntaje);
    List<PuntajeIndicador> findByEntrevistaId(Long idEntrevista);
    void deleteByEntrevistaId(Long idEntrevista);
}