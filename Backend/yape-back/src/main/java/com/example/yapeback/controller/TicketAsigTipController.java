package com.example.yapeback.controller;

import com.example.yapeback.model.TicketAsigTip;
import com.example.yapeback.service.TicketAsigTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-asesor/{idEmpleado}/ticket")
public class TicketAsigTipController {

    @Autowired
    private TicketAsigTipService ticketAsigTipService;

    // Ruta para asignar una tipificación a una conversación
    @PostMapping("/asignar")
    public void assignTicket(@PathVariable Long idEmpleado, @RequestBody TicketAsigTip ticket) {
        // Aquí puedes verificar si el asesor tiene acceso a esa conversación, etc.
        ticketAsigTipService.assignTicket(ticket);
    }
}
