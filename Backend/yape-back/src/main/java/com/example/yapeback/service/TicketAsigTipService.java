package com.example.yapeback.service;

import com.example.yapeback.interfaces.TicketAsigTipRepository;
import com.example.yapeback.model.TicketAsigTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketAsigTipService {

    @Autowired
    private TicketAsigTipRepository ticketAsigTipRepository;

    // Método para asignar una tipificación a una conversación
    public void assignTicket(TicketAsigTip ticket) {
        ticketAsigTipRepository.insertTicketAsigTip(ticket);
    }
    public boolean actualizarTicket(Long idTicketAsigTip, Long codEtiqueta, String problemaIdent, String nombreEstado, String comentario) {
        return ticketAsigTipRepository.actualizarTicket(idTicketAsigTip, codEtiqueta, problemaIdent, nombreEstado, comentario);
    }
    // Método para obtener un ticket por su ID
    public TicketAsigTip getTicketById(Long idTicketAsigTip) {
        return ticketAsigTipRepository.findById(idTicketAsigTip); // Aquí usamos un método en el repositorio para encontrar el ticket
    }
    // Método para obtener todos los tickets
    public List<TicketAsigTip> getAllTicketsByEmpl(Long idempleado) {
        return ticketAsigTipRepository.getAllTickets(idempleado);
    }
    public List<String> getEstados(){
        return ticketAsigTipRepository.getEstados();
    }

}
