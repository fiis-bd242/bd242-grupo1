package com.example.yapeback.service;

import com.example.yapeback.interfaces.EtiquetaBusquedaRepository;
import com.example.yapeback.model.EtiquetaBusqueda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetaBusquedaService {

    @Autowired
    private EtiquetaBusquedaRepository etiquetaBusquedaRepository;

    public List<EtiquetaBusqueda> buscarEtiqueta(String busqueda, String tipologia, String funcionalidad, String motivo) {
        return etiquetaBusquedaRepository.buscarEtiqueta(busqueda,tipologia, funcionalidad,motivo);
    }
}
