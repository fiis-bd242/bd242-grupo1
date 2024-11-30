package com.example.yapeback.service;

import com.example.yapeback.interfaces.ObservacionRepository;
import com.example.yapeback.model.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ObservacionRepository observacionRepository;

    public String getEmailContent(Long idPostulante) {
        List<Observacion> observaciones = observacionRepository.findByPostulanteId(idPostulante);
        StringBuilder emailBody = new StringBuilder("Estimado/a,\n\n");
        emailBody.append("Le informamos que la vacante a la que postuló ha sido cerrada.\n\n");
        emailBody.append("Feedback sobre su postulación:\n\n");

        for (Observacion observacion : observaciones) {
            emailBody.append("• ").append(observacion.getDescripcion()).append("\n");
        }

        emailBody.append("\nGracias por su participación.\n");
        return emailBody.toString();
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tu_correo@tu-dominio.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendObservacionesEmail(Long idPostulante, String to, String subject) {
        String emailContent = getEmailContent(idPostulante);
        sendEmail(to, subject, emailContent);
    }
}