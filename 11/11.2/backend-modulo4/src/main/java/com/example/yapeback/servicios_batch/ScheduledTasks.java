// src/main/java/com/example/yapeback/servicios_batch/ScheduledTasks.java
package com.example.yapeback.servicios_batch;

import com.example.yapeback.interfaces.VacanteRepository;
import com.example.yapeback.model.Convocatoria;
import com.example.yapeback.model.Vacante; // Add this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private VacanteRepository vacanteRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateVacanteStatus() {
        List<Vacante> vacantes = vacanteRepository.findAll();
        for (Vacante vacante : vacantes) {
            if (vacante.getFecha_fin().before(new java.util.Date()) &&
                    !"Vencida".equals(vacante.getEstado()) &&
                    !"Cerrada".equals(vacante.getEstado())) {
                vacante.setEstado("Vencida");
                vacanteRepository.save(vacante);
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateConvocatoriaStatus() {
        List<Convocatoria> convocatorias = vacanteRepository.findAllConvocatorias();
        for (Convocatoria convocatoria : convocatorias) {
            if (convocatoria.getFecha_fin().before(new java.util.Date()) &&
                    !"cerrada".equals(convocatoria.getEstado())) {
                convocatoria.setEstado("cerrada");
                vacanteRepository.saveConvocatoria(convocatoria);
            }
        }
    }
}