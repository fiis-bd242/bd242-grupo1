package com.example.Promo.Controller;

import com.example.Promo.Models.GestionPromosModel;
import com.example.Promo.Service.GestionPromosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestionpromos")
@CrossOrigin(origins = "${url.client}")
public class GestionPromosController {

    @Autowired
    private GestionPromosService gestionPromosService;

    @GetMapping("/mostrarpromos")
    public List<GestionPromosModel> mostrarPromos() {
        return gestionPromosService.mostrarPromociones();
    }

    @GetMapping("/filtrar")
    public List<GestionPromosModel> filtrarPromociones(
            @RequestParam(required = false) Integer vigencia,
            @RequestParam(required = false) String seller) {
        return gestionPromosService.filtrarPromociones(vigencia, seller);
    }

    @PutMapping("/actualizarEstados")
    public ResponseEntity<String> actualizarEstados(@RequestBody List<GestionPromosModel> promociones) {
        if (promociones == null || promociones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La lista de promociones está vacía.");
        }
        try {
            gestionPromosService.actualizarEstados(promociones);
            return ResponseEntity.ok("Estados actualizados correctamente.");
        } catch (Exception e) {
            e.printStackTrace(); // Agrega esto para ver el error en los logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar los estados.");
        }

    }


    @DeleteMapping("/eliminar/{codPromocion}")
    public void eliminarPromocion(@PathVariable Integer codPromocion) {
        gestionPromosService.eliminarPromocion(codPromocion);
    }
}