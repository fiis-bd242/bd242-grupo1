package com.example.yapeback.interfaces;

import com.example.yapeback.model.Monitoreo;

import java.util.List;

public interface MonitoreoRepository {

    // Método para obtener el último código de etiqueta
    String obtenerUltimoCodEtiqueta();

    // Método para obtener el historial de monitoreo
    List<Monitoreo> obtenerHistorialMonitoreo();
}
