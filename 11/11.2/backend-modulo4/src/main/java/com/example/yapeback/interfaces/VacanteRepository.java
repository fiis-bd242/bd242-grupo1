// src/main/java/com/example/yapeback/interfaces/VacanteRepository.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Convocatoria;
import com.example.yapeback.model.Postulante;
import com.example.yapeback.model.Vacante;
import java.util.List;

public interface VacanteRepository {
    List<Vacante> findAll();
    Vacante findById(Long id);
    Vacante save(Vacante vacante);
    void deleteById(Long id);
    void deleteConvocatoriaById(Long id);

    void deleteConvocatoriaByVacanteId(Long idVacante, Long idConvocatoria);
    Convocatoria saveConvocatoria(Convocatoria convocatoria);
    List<Postulante> findPostulantesByVacanteId(Long idVacante);
    Convocatoria findConvocatoriaById(Long id);
    List<Convocatoria> findAllConvocatorias(); // Add this method
    void deleteOfertaLaboralByPostulanteId(Long idPostulante);
    void deleteOfertaLaboralByVacanteId(Long idVacante);
}