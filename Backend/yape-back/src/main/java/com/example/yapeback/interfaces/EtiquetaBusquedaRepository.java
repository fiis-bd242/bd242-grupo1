package com.example.yapeback.interfaces;

import com.example.yapeback.model.EtiquetaBusqueda;

import java.util.List;

public interface EtiquetaBusquedaRepository {

    List<EtiquetaBusqueda> buscarEtiqueta(String busqueda);
}
