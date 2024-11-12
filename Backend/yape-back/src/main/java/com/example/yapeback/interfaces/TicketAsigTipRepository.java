package com.example.yapeback.interfaces;

import com.example.yapeback.model.TicketAsigTip;

public interface TicketAsigTipRepository {
   void insertTicketAsigTip(TicketAsigTip ticket);
   boolean actualizarTicket(Long idTicketAsigTip, Long codEtiqueta, String problemaIdent, Long idConv, Long idEstado, String comentario);

}
