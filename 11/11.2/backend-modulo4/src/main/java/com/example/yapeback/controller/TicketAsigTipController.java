package com.example.yapeback.controller;

import com.example.yapeback.model.TicketAsigTip;
import com.example.yapeback.model.TicketData;
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
    public void assignTicket(@PathVariable Integer idEmpleado, @RequestBody TicketAsigTip ticket) {
        // Aquí puedes verificar si el asesor tiene acceso a esa conversación, etc.
        ticketAsigTipService.assignTicket(ticket,idEmpleado);
    }
    @PutMapping("/actualizar/{idTicket}")
    public ResponseEntity<String> actualizarTicket(
            @PathVariable Integer idEmpleado,
            @PathVariable Integer idTicket,
            @RequestBody TicketData ticketData) {

        // Intentar realizar la actualización y devolver el resultado directamente
        boolean actualizado = ticketAsigTipService.actualizarTicket(
                idTicket,
                ticketData.getCodEtiqueta(),
                ticketData.getProblemaIdent(),
                ticketData.getNombreEstado().toString(),
                ticketData.getComentario(),
                idEmpleado
        );

        // Devolver directamente la respuesta sin manejar errores
        if (actualizado) {
            return ResponseEntity.ok("Ticket actualizado correctamente");
        } else {
            return ResponseEntity.status(500).body("Error al actualizar el ticket. El ticket no fue encontrado.");
        }
    }




    // Ruta para obtener un ticket por su ID
    @GetMapping("/{idTicket}")
    public ResponseEntity<TicketAsigTip> getTicket(@PathVariable Integer idEmpleado,@PathVariable Long idTicket) {
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
