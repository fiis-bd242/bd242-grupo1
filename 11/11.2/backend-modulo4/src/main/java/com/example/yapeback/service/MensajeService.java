package com.example.yapeback.service;

import com.example.yapeback.interfaces.MensajeRepository;
import com.example.yapeback.model.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    public List<Mensaje> findMessages(Long idConv, Long idEmpleado, Long idCliente) {
        return mensajeRepository.findMessagesByConversationAndAdvisor(idConv, idEmpleado, idCliente);
    }
}
