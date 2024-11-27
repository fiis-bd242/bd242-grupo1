package com.example.yapeback.interfaces;

import com.example.yapeback.model.Permiso;
import java.util.List;
import java.util.Map;

public interface PermisoRepository {
    Permiso save(Permiso permiso);  // Crear permiso
    List<Map<String, Object>> findByEmpleado(Long idEmpleado);  // Lista de permisos de un empleado
    List<Map<String, Object>> findAllWithFilters(String estado, String tipoPermiso);  // Lista de permisos para el administrador con filtros
    void updateEstado(Long idPermiso, String nuevoEstado, String comentarioAdmin);  // Aprobar o rechazar un permiso
    Map<String, Object> findDetallePermiso(Long idPermiso);
}
