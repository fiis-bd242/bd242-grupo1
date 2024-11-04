package com.example.yapeback.interfaces;

import com.example.yapeback.model.TipoEntrevista;
import java.util.List;
import com.example.yapeback.model.Indicador;
import com.example.yapeback.model.TipoEntrevistaDTO;

public interface TipoEntrevistaRepository {
    boolean existsById(Long id);
    List<Indicador> findIndicadoresByTipoId(Long idTipoEntrevista);
    List<String> findAllNombres();
    List<TipoEntrevistaDTO> findAllIdAndNombres();
}