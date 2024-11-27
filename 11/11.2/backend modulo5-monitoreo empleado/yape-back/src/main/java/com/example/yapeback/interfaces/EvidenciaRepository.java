package com.example.yapeback.interfaces;

import com.example.yapeback.model.EvidenciaPermiso;
import java.util.List;
import java.util.Map;

public interface EvidenciaRepository {
    void guardarEvidencia(EvidenciaPermiso evidenciaPermiso);
    List<Map<String, Object>> obtenerEvidenciasPorPermiso(Long idPermiso);
}