package com.example.yapeback.interfaces;

import com.example.yapeback.model.Notificacion;

public interface AnalistaRepository {

    void insertarSugerencia(Notificacion notificacion);

    void actualizarEtiquetaAsignada(int idTicketAsigTip, int etiqueta, int etiquetaCambiada, String mensajeAlAsesor,int idEmpleado);
}
