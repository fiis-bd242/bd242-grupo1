package com.example.yapeback.service;

import com.example.yapeback.interfaces.AnalistaRepository;
import com.example.yapeback.model.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalistaService {

    @Autowired
    private AnalistaRepository analistaRepository;

    public void insertarSugerencia(Notificacion notificacion) {
        analistaRepository.insertarSugerencia(notificacion);
    }

    public void actualizarEtiquetaAsignada(int idTicketAsigTip, int etiqueta, int etiquetaCambiada, String mensajeAlAsesor,int idEmpleado) {
        analistaRepository.actualizarEtiquetaAsignada(idTicketAsigTip,etiqueta, etiquetaCambiada,mensajeAlAsesor,idEmpleado);
    }
}
