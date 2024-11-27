package com.example.Promo.Service;

import com.example.Promo.Models.GestionPromosModel;
import com.example.Promo.Repository.GestionPromosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionPromosService {

    @Autowired
    private GestionPromosRepository gestionPromosRepository;

    public List<GestionPromosModel> mostrarPromociones() {
        return gestionPromosRepository.mostrarPromociones();
    }

    public List<GestionPromosModel> filtrarPromociones(Integer vigencia, String seller) {
        return gestionPromosRepository.filtrarPromociones(vigencia, seller);
    }

    public void actualizarEstados(List<GestionPromosModel> promociones) {
        for (GestionPromosModel promo : promociones) {
            gestionPromosRepository.actualizarEstado(promo.getCodPromocion(), promo.isEstadoPromo());
        }
    }




    public void eliminarPromocion(Integer codPromocion) {
        gestionPromosRepository.eliminarPromocion(codPromocion);
    }
}