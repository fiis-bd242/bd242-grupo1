package com.example.Promo.Controller;

import com.example.Promo.Models.PromocionesActivasModel;
import com.example.Promo.Service.PromocionesActivasService;
import com.example.Promo.Models.ProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")  // Ajusta según la URL de tu frontend

@RestController
public class PromocionesActivasController {

    @Autowired
    private PromocionesActivasService promocionesActivasService;

    // Obtener lista de promociones activas
    @GetMapping("/promociones-activas")
    public List<PromocionesActivasModel> getPromocionesActivas() {
        return promocionesActivasService.obtenerPromocionesActivas();
    }

    // Obtener productos por código de promoción
    @GetMapping("/productos-por-promocion/{cod_promocion}")
    public List<ProductoModel> getProductosPorPromocion(@PathVariable("cod_promocion") Integer codPromocion) {
        return promocionesActivasService.obtenerProductosPorPromocion(codPromocion);
    }
}