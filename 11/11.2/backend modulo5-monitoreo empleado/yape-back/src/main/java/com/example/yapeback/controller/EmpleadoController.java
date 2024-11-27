package com.example.yapeback.controller;

import com.example.yapeback.model.Empleado;
import com.example.yapeback.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/all") 
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        try {
            List<Empleado> empleados = empleadoService.findAll();
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        try {
            Empleado empleado = empleadoService.findById(id);
            if (empleado != null) {
                return new ResponseEntity<>(empleado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        try {
            Empleado newEmpleado = empleadoService.save(empleado);
            return new ResponseEntity<>(newEmpleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        try {
            Empleado existingEmpleado = empleadoService.findById(id);
            if (existingEmpleado != null) {
                empleado.setId_empleado(id);
                Empleado updatedEmpleado = empleadoService.save(empleado);
                return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        try {
            Empleado existingEmpleado = empleadoService.findById(id);
            if (existingEmpleado != null) {
                empleadoService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    // Lista básica de empleados usando filtros opcionales
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEmpleados(
            @RequestParam(required = false) Long idEmpleado,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String puesto,
            @RequestParam(required = false) String departamento,
            @RequestParam(required = false) String estado) {
        try {
            List<Map<String, Object>> empleados = empleadoService.findByFilters(idEmpleado, nombre, apellido, puesto, departamento, estado);
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // Detalles completos de un empleado
    @GetMapping("/{idEmpleado}/detalles")
    public ResponseEntity<Map<String, Object>> getEmpleadoDetails(@PathVariable Long idEmpleado) {
        try {
            Map<String, Object> detalles = empleadoService.findDetailsById(idEmpleado);
            if (detalles != null) {
                return new ResponseEntity<>(detalles, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}