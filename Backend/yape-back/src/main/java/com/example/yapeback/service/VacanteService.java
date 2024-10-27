
// src/main/java/com/example/yapeback/service/VacanteService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.VacanteRepository;
import com.example.yapeback.model.Postulante;
import com.example.yapeback.model.Vacante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanteService {

    private final VacanteRepository vacanteRepository;

    @Autowired
    public VacanteService(VacanteRepository vacanteRepository) {
        this.vacanteRepository = vacanteRepository;
    }

    public List<Vacante> findAll() {
        return vacanteRepository.findAll();
    }

    public Vacante findById(Long id) {
        return vacanteRepository.findById(id);
    }

    public Vacante save(Vacante vacante) {
        return vacanteRepository.save(vacante);
    }

    public void deleteById(Long id) {
        vacanteRepository.deleteById(id);
    }

    public void deleteConvocatoriaById(Long id) {
        vacanteRepository.deleteConvocatoriaById(id);
    }

    public void deleteConvocatoriaByVacanteId(Long idVacante, Long idConvocatoria) {
        vacanteRepository.deleteConvocatoriaByVacanteId(idVacante, idConvocatoria);
    }

    public List<Postulante> findPostulantesByVacanteId(Long idVacante) {
        return vacanteRepository.findPostulantesByVacanteId(idVacante);
    }
}