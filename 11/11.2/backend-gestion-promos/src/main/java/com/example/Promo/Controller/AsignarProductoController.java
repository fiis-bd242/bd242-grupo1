package com.example.Promo.Controller;

import com.example.Promo.Models.AsignarProductoRequest;
import com.example.Promo.Service.AsignarProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignarProducto")
@CrossOrigin(origins = "${url.client}")
public class AsignarProductoController {

    @Autowired
    private AsignarProductoService asignarProductoService;

    // Obtener tabla de promociones
    @GetMapping("/promociones")
    public ResponseEntity<List<Object[]>> obtenerPromociones() {
        try {
            List<Object[]> promociones = asignarProductoService.obtenerPromociones();
            return ResponseEntity.ok(promociones);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Obtener tabla de productos
    @GetMapping("/productos")
    public ResponseEntity<List<Object[]>> obtenerProductos() {
        try {
            List<Object[]> productos = asignarProductoService.obtenerProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Obtener tabla de productoxseller
    @GetMapping("/productoxseller")
    public ResponseEntity<List<Object[]>> obtenerProductoxSeller() {
        try {
            List<Object[]> productoxseller = asignarProductoService.obtenerProductoxSeller();
            return ResponseEntity.ok(productoxseller);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // Asignar productos a una promoción
    @PostMapping("/asignar")
    public ResponseEntity<String> asignarProductos(@RequestBody AsignarProductoRequest request) {
        try {
            asignarProductoService.asignarProductos(request.getCodPromocion(), request.getCodigosProductos());
            return ResponseEntity.ok("Productos asignados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al asignar productos.");
        }
    }

}