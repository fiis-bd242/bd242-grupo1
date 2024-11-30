package com.example.yapeback.interfaces;

import com.example.yapeback.model.Entrevista;
import java.util.Optional;

public interface EntrevistaRepository {
    Entrevista save(Entrevista entrevista);
    Entrevista update(Entrevista entrevista);
    Optional<Entrevista> findById(Long id);
    void deleteById(Long id);
}