package com.example.backend_capacitaciones_yape.controller;

import com.example.backend_capacitaciones_yape.model.Empleado;
import com.example.backend_capacitaciones_yape.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Endpoint para obtener los datos del empleado por su ID
    @GetMapping("/api/empleado/{id}")
    public Empleado obtenerEmpleado(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        if (empleado.isPresent()) {
            return empleado.get();
        } else {
            // Si no se encuentra el empleado, se puede devolver un error o mensaje adecuado
            throw new RuntimeException("Empleado no encontrado");
        }
    }
}