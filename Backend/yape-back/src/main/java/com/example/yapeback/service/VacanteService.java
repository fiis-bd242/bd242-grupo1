// src/main/java/com/example/yapeback/service/VacanteService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.VacanteRepository;
import com.example.yapeback.model.Convocatoria; // Add this import
import com.example.yapeback.model.Postulante;
import com.example.yapeback.model.Vacante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanteService {

    private final VacanteRepository vacanteRepository;

    @Autowired
    private PostulanteService postulanteService; // Add this field

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
        // 1. Get all postulantes for this vacante
        List<Postulante> postulantes = vacanteRepository.findPostulantesByVacanteId(id);
        
        // 2. Delete all convocatorias
        List<Convocatoria> convocatorias = vacanteRepository.findAllConvocatorias();
        for (Convocatoria convocatoria : convocatorias) {
            if (convocatoria.getId_vacante().equals(id)) {
                vacanteRepository.deleteConvocatoriaById(convocatoria.getId_convocatoria());
            }
        }

        // 3. Delete all ofertas_laborales for each postulante
        for (Postulante postulante : postulantes) {
            vacanteRepository.deleteOfertaLaboralByPostulanteId(postulante.getId_postulante());
        }
        
        // 4. Delete all postulantes using the existing endpoint service
        for (Postulante postulante : postulantes) {
            postulanteService.deleteById(postulante.getId_postulante());
        }
        
        // 5. Delete all oferta_laboral records associated with the vacante
        vacanteRepository.deleteOfertaLaboralByVacanteId(id);
        
        // 6. Finally delete the vacante itself
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

    public Convocatoria saveConvocatoria(Convocatoria convocatoria) {
        return vacanteRepository.saveConvocatoria(convocatoria);
    }

    public Convocatoria findConvocatoriaById(Long id) {
        return vacanteRepository.findConvocatoriaById(id);
    }
}