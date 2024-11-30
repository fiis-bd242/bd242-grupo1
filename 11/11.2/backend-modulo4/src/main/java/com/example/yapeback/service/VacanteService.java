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
    private EmailService emailService;

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
        Vacante existingVacante = null;
        if (vacante.getId_vacante() != null) {
            existingVacante = vacanteRepository.findById(vacante.getId_vacante());
        }

        // Guardar la vacante
        Vacante savedVacante = vacanteRepository.save(vacante);

        // Verificar si el estado cambió a "Cerrada"
        if (existingVacante != null && 
            !"Cerrada".equals(existingVacante.getEstado()) && 
            "Cerrada".equals(vacante.getEstado())) {
            
            // Obtener postulantes y enviar correos
            List<Postulante> postulantes = vacanteRepository.findPostulantesByVacanteId(vacante.getId_vacante());
            for (Postulante postulante : postulantes) {
                String emailContent = String.format(
                    "Estimado/a %s,\n\n" +
                    "Le informamos que la vacante a la que postuló ha sido cerrada.\n\n" +
                    "Feedback sobre su postulación:\n%s\n\n" +
                    "Gracias por su participación.",
                    postulante.getNombre(),
                    vacante.getComentario()
                );
                
                emailService.sendEmail(
                    postulante.getCorreo(),
                    "Actualización sobre su postulación",
                    emailContent
                );
            }
        }

        return savedVacante;
    }

    // Método para manejar la actualización cuando se contrata a alguien
    public void handleContratacion(Long vacanteId) {
        Vacante vacante = vacanteRepository.findById(vacanteId);
        if (vacante != null) {
            int nuevaCantidad = vacante.getCantidad() - 1;
            vacante.setCantidad(nuevaCantidad);
            
            // Si la cantidad llega a 0, cerrar la vacante
            if (nuevaCantidad == 0) {
                vacante.setEstado("Cerrada");
                
                // Obtener postulantes y enviar correos
                List<Postulante> postulantes = vacanteRepository.findPostulantesByVacanteId(vacanteId);
                for (Postulante postulante : postulantes) {
                    String emailContent = String.format(
                        "Estimado/a %s,\n\n" +
                        "Le informamos que la vacante a la que postuló ha sido cerrada debido a que se completó el proceso de contratación.\n\n" +
                        "Feedback sobre su postulación:\n%s\n\n" +
                        "Gracias por su participación.",
                        postulante.getNombre(),
                        vacante.getComentario()
                    );
                    
                    emailService.sendEmail(
                        postulante.getCorreo(),
                        "Actualización sobre su postulación",
                        emailContent
                    );
                }
            }
            
            vacanteRepository.save(vacante);
        }
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