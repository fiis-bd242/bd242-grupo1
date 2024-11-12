package com.example.yapeback.controller;


import com.example.yapeback.service.AnalistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analista/{id_empleado}")
public class AnalistaController {

    @Autowired
    private AnalistaService analistaService;

    @PostMapping("/sugerencia")
    public void insertarSugerencia(
            @PathVariable("id_empleado") int idEmpleado,
            @RequestParam int codTicketAsig,
            @RequestParam int codEtiqueta,
            @RequestParam String tipo,
            @RequestParam String mensaje) {

        analistaService.insertarSugerencia(codTicketAsig, codEtiqueta, tipo, mensaje);
    }

    @PutMapping("/actualizar-etiqueta")
    public void actualizarEtiquetaAsignada(
            @PathVariable("id_empleado") int idEmpleado,
            @RequestParam int idTicketAsigTip,
            @RequestParam int nuevoCodEtiqueta) {

        analistaService.actualizarEtiquetaAsignada(idTicketAsigTip, nuevoCodEtiqueta);
    }
}
