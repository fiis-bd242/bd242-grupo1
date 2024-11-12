package com.example.yapeback.interfaces;

import com.example.yapeback.model.Mensaje;

import java.util.List;

public interface MensajeRepository {
    List<Mensaje> findMessagesByConversationAndAdvisor(Long idConv, Long idEmpleado, Long idCliente);
}
