package com.example.yapeback.controller;

import com.example.yapeback.model.Permiso;
import com.example.yapeback.model.EvidenciaPermiso;
import com.example.yapeback.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/permisos")
public class PermisoController {
	
	private static final Logger log = LoggerFactory.getLogger(PermisoController.class);

    @Autowired
    private PermisoService permisoService;

    @PostMapping
    public ResponseEntity<Permiso> guardarPermiso(@RequestBody Permiso permiso) {
        Permiso permisoGuardado = permisoService.guardarPermiso(permiso);
        return new ResponseEntity<>(permisoGuardado, HttpStatus.CREATED);
    }
    
    @PostMapping("/{idPermiso}/evidencias")
    public ResponseEntity<?> subirEvidencias(
            @PathVariable Long idPermiso,
            @RequestParam("evidencias") MultipartFile[] evidencias) {
        try {
            permisoService.guardarEvidencias(idPermiso, evidencias);
            return ResponseEntity.ok("Evidencias subidas exitosamente.");
        } catch (Exception e) {
            log.error("Error al guardar evidencias para permiso con ID {}: {}", idPermiso, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir las evidencias.");
        }
    }
    
    @GetMapping("/{idPermiso}/evidencias")
    public ResponseEntity<List<Map<String, Object>>> obtenerEvidencias(@PathVariable Long idPermiso) {
        List<Map<String, Object>> evidencias = permisoService.obtenerEvidenciasPorPermiso(idPermiso);
        if (evidencias.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evidencias, HttpStatus.OK);
    }

   // @GetMapping("/{idPermiso}/evidencias")
  //  public ResponseEntity<List<EvidenciaPermiso>> obtenerEvidencias(@PathVariable Long idPermiso) {
    //    List<EvidenciaPermiso> evidencias = permisoService.obtenerEvidenciasPorPermiso(idPermiso);
    //    return new ResponseEntity<>(evidencias, HttpStatus.OK);
   // }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<Map<String, Object>>> obtenerPermisosEmpleado(@PathVariable Long idEmpleado) {
        return new ResponseEntity<>(permisoService.obtenerPermisosEmpleado(idEmpleado), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerPermisosConFiltros(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String tipoPermiso) {
        return new ResponseEntity<>(permisoService.obtenerPermisosConFiltros(estado, tipoPermiso), HttpStatus.OK);
    }
    
    @PutMapping("/{idPermiso}")
    public ResponseEntity<Void> actualizarEstadoPermiso(
            @PathVariable Long idPermiso,
            @RequestParam String nuevoEstado,
            @RequestParam(required = false) String comentarioAdmin) {
        log.info("Recibiendo actualización de estado para permiso {}: estado={}, comentario={}", idPermiso, nuevoEstado, comentarioAdmin);
        permisoService.actualizarEstadoPermiso(idPermiso, nuevoEstado, comentarioAdmin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{idPermiso}/detalles")
    public ResponseEntity<Map<String, Object>> obtenerDetallePermiso(@PathVariable Long idPermiso) {
        Map<String, Object> detalles = permisoService.obtenerDetallePermiso(idPermiso);
        return new ResponseEntity<>(detalles, HttpStatus.OK);
    }
}
