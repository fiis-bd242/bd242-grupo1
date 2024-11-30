package com.example.yapeback.controller;


import com.example.yapeback.model.EtiquetaEdicion;
import com.example.yapeback.model.Notificacion;
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
            @PathVariable("id_empleado") int idEmpleado, // ID del empleado autenticado
            @RequestBody Notificacion notificacion) {   // El objeto Notificacion contiene todos los datos

        // Asignamos el id_empleado recibido en el PathVariable al objeto Notificacion
        notificacion.setIdEmpleado(idEmpleado);

        // Llamamos al servicio para insertar la sugerencia
        analistaService.insertarSugerencia(notificacion);
    }

    @PutMapping("/actualizar-etiqueta/{idTicketAsigTip}")
    public void actualizarEtiquetaAsignada(
            @PathVariable("idTicketAsigTip") int idTicketAsigTip,
            @PathVariable("id_empleado") int idEmpleado,
            @RequestBody EtiquetaEdicion etiquetaRequest) {

        // Llama al servicio pasando todos los parámetros necesarios
        analistaService.actualizarEtiquetaAsignada(
                idTicketAsigTip,
                etiquetaRequest.getEtiqueta(),
                etiquetaRequest.getEtiquetaCambiada(),
                etiquetaRequest.getMensaje(),
                idEmpleado
        );
    }

}
