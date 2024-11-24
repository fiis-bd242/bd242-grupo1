package com.example.yapeback.controller;

import com.example.yapeback.model.EtiquetaBusqueda;
import com.example.yapeback.service.EtiquetaBusquedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api-asesor/{idEmpleado}/etiqueta")
public class EtiquetaBusquedaController {

    @Autowired
    private EtiquetaBusquedaService etiquetaBusquedaService;

    @GetMapping("/buscar")
    public ResponseEntity<List<EtiquetaBusqueda>> buscarEtiquetas(
            @PathVariable Long idEmpleado,
            @RequestParam String busqueda,
            @RequestParam(required = false) String tipologia,
            @RequestParam(required = false) String funcionalidad,
            @RequestParam(required = false) String motivo) {

        List<EtiquetaBusqueda> resultados = etiquetaBusquedaService.buscarEtiqueta(busqueda, tipologia, funcionalidad, motivo);

        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }


        return ResponseEntity.ok(resultados);
    }

}
