package com.example.yapeback.controller;

import com.example.yapeback.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrevistas")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/{id}/email-content")
    public String getEmailContent(@PathVariable Long id) {
        return emailService.getEmailContent(id);
    }

    @PostMapping("/{id}/send-email")
    public void sendEmail(@PathVariable Long id, @RequestParam String to, @RequestParam String subject) {
        emailService.sendObservacionesEmail(id, to, subject);
    }
}