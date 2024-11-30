package com.example.Promo.Controller;

import com.example.Promo.Models.CrearPromocionModel;
import com.example.Promo.Models.ProductoPromocionDTO;
import com.example.Promo.Service.CrearPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crearPromocion")
@CrossOrigin(origins = "${url.client}")
public class CrearPromocionController {

    @Autowired
    CrearPromocionService crearPromocionService;

    // Crear una nueva promoción
    @PostMapping("/nueva")
    public String crearPromocion(@RequestBody CrearPromocionModel promocion) {
        return crearPromocionService.crearPromocion(promocion);
    }


    // Asociar productos a una promoción existente
    @PostMapping("/asociarProductos/{codPromocion}")
    public String asociarProductos(@PathVariable int codPromocion, @RequestBody List<ProductoPromocionDTO> productos) {
        return crearPromocionService.asociarProductos(codPromocion, productos);
    }

    // Obtener todas las promociones
    @GetMapping("/todas")
    public List<CrearPromocionModel> obtenerTodasPromociones() {
        return crearPromocionService.obtenerTodasPromociones();
    }
}
