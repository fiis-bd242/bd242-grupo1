package com.example.yapeback.interfaces;

import com.example.yapeback.model.HistorialTipificacion;
import com.example.yapeback.model.Tipificacion;
import com.example.yapeback.model.TipificacionViewModel;

import java.util.List;

public interface TipificacionRepository {
    List<TipificacionViewModel> obtenerListaTipificaciones();
    void crearTipificacion(Tipificacion tipificacion);
    List<HistorialTipificacion> obtenerHistorialTipificaciones();
}
