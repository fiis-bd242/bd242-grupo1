package com.example.yapeback.interfaces;

import com.example.yapeback.model.Audiencia;

public interface AudienciaInterface {
    Audiencia crearAudiencia(Audiencia audiencia);
    Audiencia buscarOCrearAudiencia(Audiencia audiencia);
}
