package com.example.yapeback.controller;

import com.example.yapeback.model.TicketAsigTip;
import com.example.yapeback.service.TicketAsigTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/actualizar/{idTicket}")
    public ResponseEntity<String> actualizarTicket(
            @PathVariable Long idTicket,
            @RequestParam Long codEtiqueta,
            @RequestParam String problemaIdent,
            @RequestParam String nombreEstado,
            @RequestParam String comentario) {
        try {
            boolean actualizado = ticketAsigTipService.actualizarTicket(idTicket, codEtiqueta, problemaIdent, nombreEstado, comentario);
            if (actualizado) {
                return ResponseEntity.ok("Ticket actualizado correctamente");
            } else {
                return ResponseEntity.status(404).body("No se encontró el ticket");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error en los datos del ticket: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno al actualizar el ticket");
        }
    }
    // Ruta para obtener un ticket por su ID
    @GetMapping("/{idTicket}")
    public ResponseEntity<TicketAsigTip> getTicket(@PathVariable Long idTicket) {
        TicketAsigTip ticket = ticketAsigTipService.getTicketById(idTicket);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.status(404).body(null); // Si no se encuentra el ticket
        }
    }
    // Ruta para obtener todos los tickets
    @GetMapping("/listar")
    public ResponseEntity<List<TicketAsigTip>> getAllTickets(@PathVariable Long idEmpleado) {
        List<TicketAsigTip> tickets = ticketAsigTipService.getAllTicketsByEmpl(idEmpleado);
        if (tickets.isEmpty()) {
            return ResponseEntity.status(204).body(tickets); // No Content
        }
        return ResponseEntity.ok(tickets);
    }
    // Agrega este método en tu controlador TicketAsigTipController
    @GetMapping("/estados")
    public ResponseEntity<List<String>> getEstadoList() {
        try {
            List<String> estados = ticketAsigTipService.getEstados();
            return ResponseEntity.ok(estados);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // En caso de error
        }
    }



}
