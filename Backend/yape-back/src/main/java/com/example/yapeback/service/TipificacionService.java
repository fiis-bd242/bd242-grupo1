package com.example.yapeback.service;

import com.example.yapeback.interfaces.TipificacionRepository;
import com.example.yapeback.model.Tipificacion;
import com.example.yapeback.model.TipificacionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipificacionService {

    @Autowired
    private TipificacionRepository tipificacionRepository;

    // Método para obtener las tipificaciones
    public List<TipificacionViewModel> obtenerListaTipificaciones() {
        return tipificacionRepository.obtenerListaTipificaciones();
    }
}
