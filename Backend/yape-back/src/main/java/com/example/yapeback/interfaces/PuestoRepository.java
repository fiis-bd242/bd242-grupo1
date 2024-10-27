// src/main/java/com/example/yapeback/interfaces/PuestoRepository.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Puesto;
import com.example.yapeback.model.Vacante;
import java.util.List;

public interface PuestoRepository {
    List<Puesto> findAll();
    Puesto findById(Long id);
    Puesto save(Puesto puesto);
    void deleteById(Long id);
    void savePuestoFuncion(Long idPuesto, Long idFuncion);
    void deletePuestoFuncionByPuestoId(Long idPuesto);
    List<Vacante> findVacantesByPuestoId(Long idPuesto); // Añadir esta línea
}