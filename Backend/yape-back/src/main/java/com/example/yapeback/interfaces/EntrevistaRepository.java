package com.example.yapeback.interfaces;

import com.example.yapeback.model.Entrevista;
import java.util.List;

public interface EntrevistaRepository {
    List<Entrevista> findAll();
    Entrevista findById(Long id);
    Entrevista save(Entrevista entrevista);
    Entrevista update(Long id, Entrevista entrevista);
    void deleteById(Long id);
}
