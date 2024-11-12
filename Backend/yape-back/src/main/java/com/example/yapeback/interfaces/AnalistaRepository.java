package com.example.yapeback.interfaces;

public interface AnalistaRepository {

    void insertarSugerencia(int codTicketAsig, int codEtiqueta, String tipo, String mensaje);

    void actualizarEtiquetaAsignada(int idTicketAsigTip, int nuevoCodEtiqueta);
}
