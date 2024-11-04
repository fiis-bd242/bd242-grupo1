package com.example.yapeback.interfaces;

import com.example.yapeback.model.Indicador;
public interface IndicadorRepository {
    Indicador findById(Long id);
}