package com.example.yapeback.interfaces;

import com.example.yapeback.model.TicketAsigTip;

import java.util.List;

public interface TicketAsigTipRepository {
   void insertTicketAsigTip(TicketAsigTip ticket,Integer realizadoPor);
   void insertHistorial(Integer codTicketAsig, Integer codEtiqueta,String tipoAccion, Integer realizadoPor);
   boolean actualizarTicket(Integer idTicketAsigTip, Integer codEtiqueta, String problemaIdent, String nombreEstado, String comentario, Integer realizadoPor);
   TicketAsigTip findById(Long idTicketAsigTip);
   List<TicketAsigTip> getAllTickets(Long idEmpleado);
   List<String> getEstados();
}
