package com.example.Promo.Controller;

import com.example.Promo.Models.EditarPromocionModel;
import com.example.Promo.Repository.EditarPromocionRepository;
import com.example.Promo.Service.EditarPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/editar-promocion")
@CrossOrigin(origins = "http://localhost:3000")
public class EditarPromocionController {

    @Autowired
    private EditarPromocionService editarPromocionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPromocion(@PathVariable Long id) {
        try {
            Optional<EditarPromocionModel> promocion = editarPromocionService.obtenerPromocionPorId(id);

            if (promocion.isPresent()) {
                return ResponseEntity.ok(promocion.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Promoción no encontrada con el ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la promoción: " + e.getMessage());
        }
    }


    @PutMapping("/guardar")
    public ResponseEntity<?> guardarPromocion(@RequestBody EditarPromocionModel promocion) {
        boolean actualizado = editarPromocionService.actualizarPromocion(promocion);
        if (actualizado) {
            return ResponseEntity.ok("Promoción actualizada exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la promoción.");
        }
    }

}