package com.example.yapeback.controller;


import com.example.yapeback.model.Monitoreo;
import com.example.yapeback.service.MonitoreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/analista/monitoreo")
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;

    @GetMapping("/ultimo-cod-etiqueta")
    public String obtenerUltimoCodEtiqueta() {
        return monitoreoService.obtenerUltimoCodEtiqueta();
    }

    @GetMapping("/historial")
    public List<Monitoreo> obtenerHistorialMonitoreo() {
        return monitoreoService.obtenerHistorialMonitoreo();
    }
}
