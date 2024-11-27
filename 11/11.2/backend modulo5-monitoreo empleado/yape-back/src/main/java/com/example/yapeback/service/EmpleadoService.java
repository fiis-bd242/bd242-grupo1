// src/main/java/com/example/yapeback/service/EmpleadoService.java
package com.example.yapeback.service;

import com.example.yapeback.model.Empleado;
import com.example.yapeback.interfaces.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public Empleado findById(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }
    
    public List<Map<String, Object>> findByFilters(Long idEmpleado, String nombre, String apellido, String puesto, String departamento, String estado) {
        return empleadoRepository.findByFilters(idEmpleado, nombre, apellido, puesto, departamento, estado);
    }
    
    public Map<String, Object> findDetailsById(Long idEmpleado) {
        return empleadoRepository.findDetailsById(idEmpleado);
    }
}