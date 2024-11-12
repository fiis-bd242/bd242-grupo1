package com.example.yapeback.service;

import com.example.yapeback.interfaces.AnalistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalistaService {

    @Autowired
    private AnalistaRepository analistaRepository;

    public void insertarSugerencia(int codTicketAsig, int codEtiqueta, String tipo, String mensaje) {
        analistaRepository.insertarSugerencia(codTicketAsig, codEtiqueta, tipo, mensaje);
    }

    public void actualizarEtiquetaAsignada(int idTicketAsigTip, int nuevoCodEtiqueta) {
        analistaRepository.actualizarEtiquetaAsignada(idTicketAsigTip, nuevoCodEtiqueta);
    }
}
