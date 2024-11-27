package com.example.Promo.Service;

import com.example.Promo.Models.PromocionesActivasModel;
import com.example.Promo.Models.ProductoModel;
import com.example.Promo.Repository.PromocionesActivasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionesActivasService {

    @Autowired
    private PromocionesActivasRepository promocionesActivasRepository;

    // Obtener promociones activas
    public List<PromocionesActivasModel> obtenerPromocionesActivas() {
        return promocionesActivasRepository.obtenerPromocionesActivas();
    }

    // Obtener productos por promoción
    public List<ProductoModel> obtenerProductosPorPromocion(Integer codPromocion) {
        return promocionesActivasRepository.obtenerProductosPorPromocion(codPromocion);
    }
}