package com.example.yapeback.controller;

import com.example.yapeback.model.Mensaje;
import com.example.yapeback.model.Conversacion;
import com.example.yapeback.service.ConversacionService;
import com.example.yapeback.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-asesor/{idEmpleado}/historial")
public class ConversacionController {

    @Autowired
    private ConversacionService conversacionService;
    @Autowired
    private MensajeService mensajeService;

    // Ruta para obtener el historial de conversaciones de un asesor
    @GetMapping
    public List<Conversacion> getHistorial(@PathVariable Long idEmpleado) {
        return conversacionService.findById(idEmpleado);
    }

    // Ruta para obtener los mensajes de una conversación específica
    @GetMapping("/{idConv}")
    public List<Mensaje> getMessages(@PathVariable Long idEmpleado, @PathVariable Long idConv, @RequestParam Long idCliente) {
        return mensajeService.findMessages(idConv, idEmpleado, idCliente);
    }
}
