package com.example.Promo.Service;

import com.example.Promo.Models.CrearPromocionModel;
import com.example.Promo.Models.ProductoPromocionDTO;
import com.example.Promo.Repository.CrearPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrearPromocionService {

    @Autowired
    CrearPromocionRepository crearPromocionRepository;

    // Crear una nueva promoción
    public String crearPromocion(CrearPromocionModel promocion) {
        return crearPromocionRepository.crearPromocion(promocion);
    }

    // Asociar productos a una promoción existente
    public String asociarProductos(int codPromocion, List<ProductoPromocionDTO> productos) {
        return crearPromocionRepository.asociarProductos(codPromocion, productos);
    }

    // Obtener todas las promociones
    public List<CrearPromocionModel> obtenerTodasPromociones() {
        return crearPromocionRepository.obtenerTodasPromociones();
    }
}