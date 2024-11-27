// src/main/java/com/example/yapeback/interfaces/EmpleadoRepository.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Empleado;
import java.util.List;
import java.util.Map;

public interface EmpleadoRepository {
    List<Empleado> findAll();
    Empleado findById(Long id);
    Empleado save(Empleado empleado);
    void deleteById(Long id);
    List<Map<String, Object>> findByFilters(Long idEmpleado, String nombre, String apellido, String puesto, String departamento, String estado);
    Map<String, Object> findDetailsById(Long idEmpleado);
}