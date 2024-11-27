package com.example.Promo.Service;

import com.example.Promo.Models.EditarPromocionModel;
import com.example.Promo.Repository.EditarPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditarPromocionService {

    @Autowired
    private EditarPromocionRepository editarPromocionRepository;

    public Optional<EditarPromocionModel> obtenerPromocionPorId(Long id) {
        return editarPromocionRepository.obtenerPromocionPorId(id);
    }

    public boolean actualizarPromocion(EditarPromocionModel promocion) {
        return editarPromocionRepository.actualizarPromocion(promocion);
    }
}