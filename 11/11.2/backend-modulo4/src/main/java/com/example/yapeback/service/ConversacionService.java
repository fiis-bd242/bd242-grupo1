package com.example.yapeback.service;

import com.example.yapeback.interfaces.ConversacionRepository;
import com.example.yapeback.model.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversacionService {
    @Autowired
    private ConversacionRepository conversacionRepository;
    public List<Conversacion> findAll() {return conversacionRepository.findAllHistorial();}
    public List<Conversacion> findById(Long id) {return conversacionRepository.findByIdEmpleado(id);}
}
