package com.example.yapeback.interfaces;

import com.example.yapeback.model.TipoEntrevista;
import java.util.List;
import com.example.yapeback.model.Indicador;

public interface TipoEntrevistaRepository {
    boolean existsById(Long id);
    List<Indicador> findIndicadoresByTipoId(Long idTipoEntrevista);
}