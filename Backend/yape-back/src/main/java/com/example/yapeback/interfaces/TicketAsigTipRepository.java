package com.example.yapeback.interfaces;

import com.example.yapeback.model.TicketAsigTip;

import java.util.List;

public interface TicketAsigTipRepository {
   void insertTicketAsigTip(TicketAsigTip ticket);
   boolean actualizarTicket(Long idTicketAsigTip, Long codEtiqueta, String problemaIdent, String nombreEstado, String comentario);
   TicketAsigTip findById(Long idTicketAsigTip);
   List<TicketAsigTip> getAllTickets(Long idEmpleado);
   List<String> getEstados();
}
