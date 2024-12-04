package com.example.demo.services;

import com.example.demo.interfaces.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Map<String, Object>> obtenerReporteDetallado() {
        return reporteRepository.reporteDetallado();
    }
}

