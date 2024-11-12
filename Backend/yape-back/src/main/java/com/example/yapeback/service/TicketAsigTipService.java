package com.example.yapeback.service;

import com.example.yapeback.interfaces.TicketAsigTipRepository;
import com.example.yapeback.model.TicketAsigTip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketAsigTipService {

    @Autowired
    private TicketAsigTipRepository ticketAsigTipRepository;

    // Método para asignar una tipificación a una conversación
    public void assignTicket(TicketAsigTip ticket) {
        ticketAsigTipRepository.insertTicketAsigTip(ticket);
    }
    public boolean actualizarTicket(Long idTicketAsigTip, Long codEtiqueta, String problemaIdent, Long idConv, Long idEstado, String comentario) {
        return ticketAsigTipRepository.actualizarTicket(idTicketAsigTip, codEtiqueta, problemaIdent, idConv, idEstado, comentario);
    }
}
