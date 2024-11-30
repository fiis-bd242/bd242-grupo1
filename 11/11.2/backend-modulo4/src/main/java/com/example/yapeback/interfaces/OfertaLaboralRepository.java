package com.example.yapeback.interfaces;

import com.example.yapeback.model.OfertaLaboral;
import java.util.List;

public interface OfertaLaboralRepository {
    List<OfertaLaboral> findAll();
    OfertaLaboral findById(Long id);
    OfertaLaboral save(OfertaLaboral ofertaLaboral);
    void deleteById(Long id);
    void updateBeneficios(OfertaLaboral ofertaLaboral);
}