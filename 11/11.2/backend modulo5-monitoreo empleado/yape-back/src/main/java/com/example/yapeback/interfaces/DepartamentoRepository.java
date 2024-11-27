// src/main/java/com/example/yapeback/interfaces/DepartamentoRepository.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Departamento;
import com.example.yapeback.model.Puesto;

import java.util.List;

public interface DepartamentoRepository {
    List<Departamento> findAll();
    Departamento findById(Long id);
    List<Departamento> findSubDepartments(Long id);
    List<Puesto> findPuestosByDepartamentoId(Long idDepartamento);
}