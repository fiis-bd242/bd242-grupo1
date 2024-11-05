// src/main/java/com/example/yapeback/interfaces/BeneficioRepository.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Beneficio;
import java.util.List;

public interface BeneficioRepository {
    List<Beneficio> findAll();
    Beneficio findById(Long id);
    Beneficio save(Beneficio beneficio);
    void deleteById(Long id);
}