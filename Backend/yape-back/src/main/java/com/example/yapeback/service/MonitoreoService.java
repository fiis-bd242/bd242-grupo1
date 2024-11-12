package com.example.yapeback.service;

import com.example.yapeback.interfaces.MonitoreoRepository;
import com.example.yapeback.model.Monitoreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonitoreoService {

    @Autowired
    private MonitoreoRepository monitoreoRepository;

    public String obtenerUltimoCodEtiqueta() {
        return monitoreoRepository.obtenerUltimoCodEtiqueta();
    }

    public List<Monitoreo> obtenerHistorialMonitoreo() {
        return monitoreoRepository.obtenerHistorialMonitoreo();
    }
}
