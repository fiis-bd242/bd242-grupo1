package com.example.yapeback.interfaces;


import com.example.yapeback.model.Conversacion;

import java.util.List;

public interface ConversacionRepository {
    List<Conversacion> findAllHistorial();
    List<Conversacion> findByIdEmpleado(Long idEmpleado);
    void actualizarEstadoConversacion(Integer codTicket, String estadoConv);
}
